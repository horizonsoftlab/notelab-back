package sn.hsl.notelabback.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sn.hsl.notelabback.entities.enums.Role;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "account")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String username;

    String password;

    boolean firstAttempt;

    boolean enabled;

    @Enumerated(EnumType.STRING)
    Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;
}
