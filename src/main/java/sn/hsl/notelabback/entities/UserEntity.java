package sn.hsl.notelabback.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sn.hsl.notelabback.entities.enums.Sexe;
import sn.hsl.notelabback.entities.enums.UserType;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;

    String lastName;

    @Column(columnDefinition = "TEXT")
    String address;

    @Column(unique = true, nullable = false)
    String email;

    String phone;

    String dateOfBirth;

    @Enumerated(EnumType.STRING)
    UserType type;

    @Enumerated(EnumType.STRING)
    Sexe sexe;

    @OneToOne(mappedBy = "user")
    AccountEntity account;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<SchoolEntity> schools = new ArrayList<>();
}
