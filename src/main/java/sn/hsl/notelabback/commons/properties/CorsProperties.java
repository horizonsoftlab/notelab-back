package sn.hsl.notelabback.commons.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.cors.CorsConfiguration;

@Component
@Validated
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "notelab-cors")
public class CorsProperties {

    final CorsConfiguration cors = new CorsConfiguration();
}
