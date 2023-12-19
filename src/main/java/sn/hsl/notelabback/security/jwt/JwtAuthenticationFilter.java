package sn.hsl.notelabback.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static sn.hsl.notelabback.commons.constants.SecurityConstant.BEARER_HEADER;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Qualifier("handlerExceptionResolver")
    @Autowired
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHORIZATION);

        if(Objects.isNull(authHeader) || !authHeader.startsWith(BEARER_HEADER)) {
            try {
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                resolveException(request, response, e);
            }
        } else {
            try {
                final String jwt = authHeader.substring(BEARER_HEADER.length());
                final String username = jwtProvider.extractUserName(jwt);

                if(StringUtils.isNotEmpty(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    var userDetails = userDetailsService.loadUserByUsername(username);
                    if(jwtProvider.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                filterChain.doFilter(request, response);

            } catch (MalformedJwtException e) {
                log.error("malFormedJwt : {}", e.getMessage());
                resolveException(request, response, e);
            } catch (ExpiredJwtException e) {
                log.error("expiredJwt : {}", e.getMessage());
                resolveException(request, response, e);
            } catch (UnsupportedJwtException e) {
                log.error("unsupportedJwt : {}", e.getMessage());
                resolveException(request, response, e);
            } catch (IllegalArgumentException e) {
                log.error("IllegalArgument : {}", e.getMessage());
                resolveException(request, response, e);
            }
        }
    }

    private void resolveException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        resolver.resolveException(request, response, null, e);
    }
}
