package sn.hsl.notelabback.security.rules;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import sn.hsl.notelabback.entities.enums.Permissions;
import sn.hsl.notelabback.security.rules.config.SecurityRule;

import static sn.hsl.notelabback.web.tools.constant.ApiConstant.CLASSROOM_API_PREFIX;
import static sn.hsl.notelabback.web.tools.constant.ApiConstant.SCHOOL_ID_API;

@Component
public class ClassroomRule {

    @Bean
    public SecurityRule createClassroom() {
        return SecurityRule.builder()
                .apiPattern(CLASSROOM_API_PREFIX  + SCHOOL_ID_API)
                .httpMethod(HttpMethod.POST)
                .build()
                .condition()
                .hasPermission(Permissions.ALL_ACCESS)
                .hasPermission(Permissions.ADD_CLASSROOM)
                .end();
    }

}
