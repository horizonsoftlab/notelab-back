package sn.hsl.notelabback.web.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sn.hsl.notelabback.entities.enums.Sexe;
import sn.hsl.notelabback.entities.enums.UserType;

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
public class UserRspDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    Long id;

    String firstName;

    String lastName;

    Sexe sexe;

    String address;

    String email;

    String phone;

    String dateOfBirth;

    AccountRspDto account;

    UserType type;
}
