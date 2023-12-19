package sn.hsl.notelabback.web.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SchoolReqDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    @NotBlank(message = "Le nom est obligatoire")
    String name;

    @NotBlank(message = "L'adresse est obligatoire")
    String address;

    String email;

    String phone;

    String website;
}
