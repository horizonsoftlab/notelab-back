package sn.hsl.notelabback.web.tools.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ApiMessage {
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Account not found", "Account with %s %s not found"),
    FAILED_TO_BUILD_EMAIL(HttpStatus.BAD_REQUEST.value(), "Failed to build email", "Impossible to build email message due to %s"),
    FAILED_TO_SEND_EMAIL(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to send email", "Impossible to send email due to %s"),
    ACCOUNT_ALREADY_EXISTS(HttpStatus.CONFLICT.value(), "Account exists", "Account with username %s already exists"),
    ACCOUNT_NOT_ACTIVATED(HttpStatus.LOCKED.value(), "Account not activate", "Account must first be activated"),
    ACCOUNT_ALREADY_ACTIVATED(HttpStatus.CONFLICT.value(), "Failed to sign in", "Account %s already activated"),
    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", "%s"),
    SCHOOL_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "School not found", "School with %s %s not found"),
    SCHOOL_CYCLE_OR_DOMAIN_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "School or cycle not found", "School, cycle and domain are required"),
    DOMAIN_NOT_CONTAIN_SPECIALITY(HttpStatus.NOT_FOUND.value(), "Domain not contain speciality", "Domain %s not contain speciality %s"),
    CYCLE_NOT_CONTAIN_LEVEL(HttpStatus.NOT_FOUND.value(), "Cycle not contain level", "Cycle %s not contain level %s"),
    SCHOOL_ALREADY_HAS_CLASSROOM(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Classroom exists", "School already has classroom %s"),
    CLASSROOM_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Classroom not found", "Classroom with %s %s not found"),
    CLASSROOM_CAPACITY_REACHED(HttpStatus.BAD_REQUEST.value(), "Classroom capacity reached", "Maximum capacity(%d) for classroom %s has reached");

    @Getter
    private final int status;

    @Getter
    private final String error;

    @Getter
    private final String message;

    ApiMessage(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
