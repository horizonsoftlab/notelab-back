package sn.hsl.notelabback.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sn.hsl.notelabback.entities.enums.Level;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Table(name = "inscription")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InscriptionEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    String year;

    @Enumerated(EnumType.STRING)
    Level level;

    @OneToOne
    UserEntity student;

    @ManyToOne
    ClassroomEntity classroom;
}
