package sn.hsl.notelabback.web.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpReqDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    @NotNull
    @Valid
    OwnerReqDto user;

    @NotNull
    @Valid
    AccountReqDto account;

    @NotNull
    @Valid
    SchoolReqDto school;
}
