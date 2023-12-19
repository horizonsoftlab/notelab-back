package sn.hsl.notelabback.web.api;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.hsl.notelabback.service.AuthenticationService;
import sn.hsl.notelabback.web.dto.request.AccountReqDto;
import sn.hsl.notelabback.web.dto.request.SignUpReqDto;
import sn.hsl.notelabback.web.tools.response.ApiSuccess;

import static sn.hsl.notelabback.web.tools.constant.ApiConstant.*;

@RestController
@RequestMapping(AUTHENTICATION_API_PREFIX)
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthenticationApi {
    final AuthenticationService authenticationService;

    @Operation(summary = "Register a school owner")
    @PostMapping(REGISTER_OWNER)
    public ResponseEntity<ApiSuccess> registerOwner(@RequestBody @Valid SignUpReqDto signUpReqDto) {
        authenticationService.registerOwner(signUpReqDto);

        return new ResponseEntity<>(ApiSuccess.build(HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @Operation(summary = "Active owner account")
    @PostMapping(ACTIVE_ACCOUNT)
    public ResponseEntity<ApiSuccess> activeAccount(@RequestBody @Valid AccountReqDto accountReqDto) {
        var body = ApiSuccess.build(HttpStatus.OK, authenticationService.activeAccount(accountReqDto));

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Operation(summary = "Sign in to a account")
    @PostMapping(SING_IN)
    public ResponseEntity<ApiSuccess> signIn(@RequestBody @Valid AccountReqDto accountReqDto) {
        var body = ApiSuccess.build(HttpStatus.OK, authenticationService.signIn(accountReqDto));

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
