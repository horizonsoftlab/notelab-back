package sn.hsl.notelabback.web.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sn.hsl.notelabback.entities.enums.Cycle;
import sn.hsl.notelabback.entities.enums.Domain;
import sn.hsl.notelabback.entities.enums.Level;
import sn.hsl.notelabback.entities.enums.Speciality;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassroomReqDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    @NotBlank
    String name;

    @NotNull
    Integer capacity;

    //only used for checking speciality coherence (will not be add in database)
    @NotNull
    Domain domain;

    //only used for checking level coherence (will not be add in database)
    @NotNull
    Cycle cycle;

    @NotNull
    Speciality speciality;

    @NotNull
    Level level;
}
