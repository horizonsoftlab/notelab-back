package sn.hsl.notelabback.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sn.hsl.notelabback.entities.enums.Level;
import sn.hsl.notelabback.entities.enums.Speciality;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "classroom")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassroomEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Integer capacity;

    @Enumerated(EnumType.STRING)
    Level level;

    @Enumerated(EnumType.STRING)
    Speciality speciality;

    @ManyToOne
    SchoolEntity school;
}
