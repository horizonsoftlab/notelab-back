package sn.hsl.notelabback.security.rules;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import sn.hsl.notelabback.entities.enums.Permissions;
import sn.hsl.notelabback.security.rules.config.SecurityRule;

import static sn.hsl.notelabback.web.tools.constant.ApiConstant.CLASSROOM_ID_API;
import static sn.hsl.notelabback.web.tools.constant.ApiConstant.INSCRIPTION_API_PREFIX;

@Component
public class InscriptionRule {

    @Bean
    public SecurityRule registerStudent() {
        return SecurityRule.builder()
                .apiPattern(INSCRIPTION_API_PREFIX + CLASSROOM_ID_API)
                .httpMethod(HttpMethod.POST)
                .build()
                .condition()
                .hasPermission(Permissions.ALL_ACCESS)
                .hasPermission(Permissions.ADD_USER)
                .end();
    }

}
