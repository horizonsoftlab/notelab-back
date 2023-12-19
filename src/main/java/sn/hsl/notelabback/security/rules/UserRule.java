package sn.hsl.notelabback.security.rules;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import sn.hsl.notelabback.entities.enums.Permissions;
import sn.hsl.notelabback.security.rules.config.SecurityRule;

import static sn.hsl.notelabback.web.tools.constant.ApiConstant.USER_API_PREFIX;

@Component
public class UserRule {

    @Bean
    public SecurityRule readAllUsers() {
        return SecurityRule.builder()
                .apiPattern(USER_API_PREFIX)
                .httpMethod(HttpMethod.GET)
                .build()
                .condition()
                .hasPermission(Permissions.ALL_ACCESS)
                .hasPermission(Permissions.READ_USERS)
                .hasPermission(Permissions.ADD_USER)
                .hasPermission(Permissions.EDIT_USER)
                .hasPermission(Permissions.DELETE_USER)
                .end();
    }

}
