package sn.hsl.notelabback.web.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

import static sn.hsl.notelabback.commons.constants.AppConstant.PASSWORD_LENGTH;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountReqDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    @NotBlank
    String username;

    @NotBlank
    @Size(min = PASSWORD_LENGTH)
    String password;
}
