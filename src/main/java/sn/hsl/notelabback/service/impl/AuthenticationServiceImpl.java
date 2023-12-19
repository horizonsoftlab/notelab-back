package sn.hsl.notelabback.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.hsl.notelabback.entities.enums.Role;
import sn.hsl.notelabback.entities.enums.UserType;
import sn.hsl.notelabback.exceptions.NotelabException;
import sn.hsl.notelabback.repository.AccountRepository;
import sn.hsl.notelabback.repository.SchoolRepository;
import sn.hsl.notelabback.security.jwt.JwtProvider;
import sn.hsl.notelabback.security.service.UserDetailsImpl;
import sn.hsl.notelabback.service.AuthenticationService;
import sn.hsl.notelabback.service.EmailService;
import sn.hsl.notelabback.web.dto.request.AccountReqDto;
import sn.hsl.notelabback.web.dto.request.SignUpReqDto;
import sn.hsl.notelabback.web.dto.response.MailRspDto;
import sn.hsl.notelabback.web.dto.response.SignInRspDto;
import sn.hsl.notelabback.web.mapper.AccountMapper;
import sn.hsl.notelabback.web.mapper.SchoolMapper;
import sn.hsl.notelabback.web.mapper.UserMapper;
import sn.hsl.notelabback.web.tools.response.ApiMessage;

import static sn.hsl.notelabback.web.tools.response.ApiMessage.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    final AccountRepository accountRepository;
    final SchoolRepository schoolRepository;
    final UserMapper userMapper;
    final AccountMapper accountMapper;
    final SchoolMapper schoolMapper;
    final EmailService emailService;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final JwtProvider jwtProvider;

    final static String ACCOUNT_CONFIRMATION_SUBJECT = "Confirmation de création de compte";

    @Transactional
    @Override
    public void registerOwner(SignUpReqDto signupReqDto) {
        if(!accountRepository.existsByUsername(signupReqDto.getAccount().getUsername())) {
            var user = userMapper.toEntity(signupReqDto.getUser());
            var account = accountMapper.toEntity(signupReqDto.getAccount());
            var school = schoolMapper.toEntity(signupReqDto.getSchool());
            final var userPassword = account.getPassword();

            user.setType(UserType.OWNER);

            account.setUser(user);
            account.setRole(Role.OWNER);
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            account.setFirstAttempt(Boolean.TRUE);
            account.setEnabled(Boolean.FALSE);
            var savedAccount = accountRepository.save(account);

            school.setOwner(savedAccount.getUser());
            schoolRepository.save(school);

            log.info("register owner account {} success", savedAccount.getUsername());

            var mailRsp = MailRspDto.builder()
                    .firstname(savedAccount.getUser().getFirstName())
                    .lastname(savedAccount.getUser().getLastName())
                    .username(savedAccount.getUsername())
                    .password(userPassword)
                    .build();

            emailService.sendWithHtml(ACCOUNT_CONFIRMATION_SUBJECT, mailRsp, savedAccount.getUser().getEmail());
        } else {
            throw new NotelabException(ACCOUNT_ALREADY_EXISTS, signupReqDto.getAccount().getUsername());
        }
    }

    @Override
    public SignInRspDto activeAccount(AccountReqDto accountReqDto) {
        if(accountRepository.isAccountFirstAttempt(accountReqDto.getUsername())) {
            var account = accountRepository.findByUsername(accountReqDto.getUsername())
                .orElseThrow(()-> new NotelabException(ApiMessage.ACCOUNT_NOT_FOUND, "username", accountReqDto.getUsername()));
            account.setEnabled(Boolean.TRUE);
            account.setFirstAttempt(Boolean.FALSE);
            accountRepository.save(account);

            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountReqDto.getUsername(), accountReqDto.getPassword()));
                return SignInRspDto.builder()
                        .access_token(jwtProvider.generateToken(UserDetailsImpl.build(account)))
                        .build();
            } catch (AuthenticationException ex) {
                account.setEnabled(Boolean.FALSE);
                account.setFirstAttempt(Boolean.TRUE);
                accountRepository.save(account);

                throw new NotelabException(AUTHENTICATION_FAILED, ex.getMessage());
            }
        } else {
            throw new NotelabException(ACCOUNT_ALREADY_ACTIVATED, accountReqDto.getUsername());
        }
    }

    @Override
    public SignInRspDto signIn(AccountReqDto accountReqDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountReqDto.getUsername(), accountReqDto.getPassword()));

        var account = accountRepository.findByUsername(accountReqDto.getUsername())
                .orElseThrow(()-> new NotelabException(ApiMessage.ACCOUNT_NOT_FOUND, "username", accountReqDto.getUsername()));

        return SignInRspDto.builder()
                .access_token(jwtProvider.generateToken(UserDetailsImpl.build(account)))
                .build();
    }
}
