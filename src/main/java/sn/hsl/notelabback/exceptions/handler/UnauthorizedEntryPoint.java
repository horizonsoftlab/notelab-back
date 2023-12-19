package sn.hsl.notelabback.exceptions.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import sn.hsl.notelabback.web.tools.response.ApiError;

import java.io.IOException;
import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Unauthorized error: {}", authException.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiError body = ApiError.builder()
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .error("Unauthorized")
                .timestamp(Instant.now())
                .message(authException.getMessage())
                .path(UrlUtils.buildFullRequestUrl(request))
                .build();

        mapper.writeValue(response.getOutputStream(), body);
    }
}
