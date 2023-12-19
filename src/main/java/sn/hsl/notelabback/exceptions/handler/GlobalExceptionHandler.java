package sn.hsl.notelabback.exceptions.handler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sn.hsl.notelabback.exceptions.NotelabException;
import sn.hsl.notelabback.web.tools.response.ApiError;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError malFormedJwtHandler(MalformedJwtException e, HttpServletRequest request) {

        log.error("malFormedJwt {}", e.getMessage());

        return ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("malFormed jwt")
                .message(e.getMessage())
                .timestamp(Instant.now())
                .path(UrlUtils.buildFullRequestUrl(request))
                .build();
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError expiredJwtHandler(ExpiredJwtException e, HttpServletRequest request) {

        log.error("expiredJwt {}", e.getMessage());

        return ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Expired jwt")
                .message(e.getMessage())
                .timestamp(Instant.now())
                .path(UrlUtils.buildFullRequestUrl(request))
                .build();
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError unsupportedJwtHandler(UnsupportedJwtException e, HttpServletRequest request) {

        log.error("unsupportedJwt {}", e.getMessage());

        return ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("unsupported jwt")
                .message(e.getMessage())
                .timestamp(Instant.now())
                .path(UrlUtils.buildFullRequestUrl(request))
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError illegalArgumentHandler(IllegalArgumentException e, HttpServletRequest request) {

        log.error("illegalArgument {}", e.getMessage());

        return ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("IllegalArgument")
                .message(e.getMessage())
                .timestamp(Instant.now())
                .path(UrlUtils.buildFullRequestUrl(request))
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {

        log.error("argumentNotValid {}", e.getMessage());

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("field(s) not valid")
                .errors(errors)
                .timestamp(Instant.now())
                .path(UrlUtils.buildFullRequestUrl(request))
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError jsonParseError(HttpMessageNotReadableException e, HttpServletRequest request) {

        log.error(e.getMessage());

        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("json parse")
                .message(e.getMessage())
                .timestamp(Instant.now())
                .path(UrlUtils.buildFullRequestUrl(request))
                .build();
    }

    @ExceptionHandler(NotelabException.class)
    public ResponseEntity<ApiError> notelabExceptionHandler(NotelabException e, HttpServletRequest request) {

        log.error("notelabException {}", e.getMessage());
        
        var error = ApiError.builder()
                .status(e.getStatus().value())
                .error(e.getError())
                .message(e.getMessage())
                .timestamp(Instant.now())
                .path(UrlUtils.buildFullRequestUrl(request))
                .build();
        return new ResponseEntity<>(error, e.getStatus());
    }
}
