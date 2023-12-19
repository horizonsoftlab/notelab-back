package sn.hsl.notelabback.web.tools.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiSuccess {
    private String status;
    private Object payload;

    public static ApiSuccess build(HttpStatus status) {
        return build(status, null);
    }

    public static ApiSuccess build(HttpStatus status, Object payload) {
        return ApiSuccess.builder()
                .status(status.name())
                .payload(payload)
                .build();
    }
}
