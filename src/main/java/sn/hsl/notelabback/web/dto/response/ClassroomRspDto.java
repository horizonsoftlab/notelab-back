package sn.hsl.notelabback.web.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sn.hsl.notelabback.entities.enums.Level;
import sn.hsl.notelabback.entities.enums.Speciality;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassroomRspDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    String name;

    Integer capacity;

    Level level;

    Speciality speciality;
}
