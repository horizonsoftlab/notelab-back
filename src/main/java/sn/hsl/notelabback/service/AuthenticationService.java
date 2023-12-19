package sn.hsl.notelabback.service;

import sn.hsl.notelabback.web.dto.request.AccountReqDto;
import sn.hsl.notelabback.web.dto.request.SignUpReqDto;
import sn.hsl.notelabback.web.dto.response.SignInRspDto;

public interface AuthenticationService {
    void registerOwner(SignUpReqDto signupReqDto);
    SignInRspDto activeAccount(AccountReqDto accountReqDto);
    SignInRspDto signIn(AccountReqDto accountReqDto);
}

