package sn.hsl.notelabback.commons.constants;

import static sn.hsl.notelabback.web.tools.constant.ApiConstant.AUTHENTICATION_API_PREFIX;

public class SecurityConstant {

    public static final String[] ENDPOINTS_WITH_NO_SECURITY = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/manage/**",
            "/api/*/v3/api-docs",
            "/api/v3/api-docs/**",
            "/api/*/v3/api-docs/**",
            "/webjars/springfox-swagger-ui/**",
            "/api/*/v2/api-docs",
            "/documentation",
            AUTHENTICATION_API_PREFIX + "/**",
    };

    public static final String BEARER_HEADER = "Bearer ";

    public static final String ACTIVE_ACCOUNT_FROM_EMAIL = "signIn";
}
