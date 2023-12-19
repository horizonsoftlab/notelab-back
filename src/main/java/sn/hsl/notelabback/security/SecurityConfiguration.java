package sn.hsl.notelabback.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import sn.hsl.notelabback.security.jwt.JwtAuthenticationFilter;
import sn.hsl.notelabback.security.rules.config.WebSecurityManager;

import static sn.hsl.notelabback.commons.constants.SecurityConstant.ENDPOINTS_WITH_NO_SECURITY;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration {
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final CorsFilter corsFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final WebSecurityManager webSecurityManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.csrf(AbstractHttpConfigurer::disable)
                 .exceptionHandling(exception -> exception
                         .authenticationEntryPoint(authenticationEntryPoint)
                         .accessDeniedHandler(accessDeniedHandler))
                 .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                 .authorizeRequests(request -> request.requestMatchers(ENDPOINTS_WITH_NO_SECURITY).permitAll())
                 .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .authenticationProvider(authenticationProvider)
                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

         webSecurityManager.manageSecurityRules(http);

         http.authorizeRequests(request -> request.anyRequest().denyAll());

         return http.build();
    }

}
