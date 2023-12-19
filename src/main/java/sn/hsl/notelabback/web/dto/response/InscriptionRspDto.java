package sn.hsl.notelabback.web.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sn.hsl.notelabback.entities.enums.Level;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InscriptionRspDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    Date date;

    String year;

    Level level;

    UserRspDto student;

    ClassroomRspDto classroom;
}
