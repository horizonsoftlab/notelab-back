package sn.hsl.notelabback.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.hsl.notelabback.entities.AccountEntity;
import sn.hsl.notelabback.entities.enums.Role;
import sn.hsl.notelabback.entities.enums.UserType;
import sn.hsl.notelabback.exceptions.NotelabException;
import sn.hsl.notelabback.repository.AccountRepository;
import sn.hsl.notelabback.repository.ClassroomRepository;
import sn.hsl.notelabback.repository.InscriptionRepository;
import sn.hsl.notelabback.service.EmailService;
import sn.hsl.notelabback.service.InscriptionService;
import sn.hsl.notelabback.web.dto.request.InscriptionReqDto;
import sn.hsl.notelabback.web.dto.response.InscriptionRspDto;
import sn.hsl.notelabback.web.dto.response.MailRspDto;
import sn.hsl.notelabback.web.mapper.InscriptionMapper;
import sn.hsl.notelabback.web.mapper.UserMapper;

import static sn.hsl.notelabback.commons.constants.AppConstant.*;
import static sn.hsl.notelabback.web.tools.response.ApiMessage.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class InscriptionServiceImpl implements InscriptionService {
    final InscriptionRepository inscriptionRepository;
    final ClassroomRepository classroomRepository;
    final AccountRepository accountRepository;
    final InscriptionMapper inscriptionMapper;
    final UserMapper userMapper;
    final PasswordEncoder passwordEncoder;
    final EmailService emailService;

    @Transactional
    @Override
    public InscriptionRspDto registerStudent(Long classroomId, InscriptionReqDto inscriptionReqDto) {
        var classroom = classroomRepository.findById(classroomId)
                .orElseThrow(()-> new NotelabException(CLASSROOM_NOT_FOUND, "id", classroomId));
        var username = new StringBuilder()
                .append(inscriptionReqDto.getStudent().getFirstName())
                .append(".")
                .append(inscriptionReqDto.getStudent().getLastName())
                .append(accountRepository.count() + 1)
                .append(USERNAME_SUFFIX)
                .toString()
                .toLowerCase();

        if(inscriptionRepository.countByClassroomIdAndYear(classroomId, inscriptionReqDto.getYear()) >= classroom.getCapacity()) {
            throw new NotelabException(CLASSROOM_CAPACITY_REACHED, classroom.getCapacity(), classroom.getName());
        } else if(accountRepository.existsByUsername(username)) {
            throw new NotelabException(ACCOUNT_ALREADY_EXISTS, username);
        }

        var student = userMapper.toEntity(inscriptionReqDto.getStudent());
        student.setType(UserType.STUDENT);

        var account = AccountEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(STUDENT_DEFAULT_PASSWORD))
                .role(Role.STUDENT)
                .enabled(Boolean.TRUE)
                .firstAttempt(Boolean.FALSE)
                .user(student)
                .build();
        var savedAccount = accountRepository.saveAndFlush(account);

        var inscription = inscriptionMapper.toEntity(inscriptionReqDto);
        inscription.setStudent(savedAccount.getUser());
        inscription.setClassroom(classroom);

        if(StringUtils.isNotBlank(student.getEmail())) {
            emailService.sendWithHtml(
                    ACCOUNT_INFORMATION,
                    MailRspDto.builder()
                            .firstname(student.getFirstName())
                            .lastname(student.getLastName())
                            .username(account.getUsername())
                            .password(STUDENT_DEFAULT_PASSWORD)
                            .build(),
                    student.getEmail());
        }

        return inscriptionMapper.toDto(inscriptionRepository.save(inscription));
    }
}
