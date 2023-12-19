package sn.hsl.notelabback.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sn.hsl.notelabback.entities.enums.Cycle;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "school")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SchoolEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5387827484974552092L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(columnDefinition = "TEXT")
    String address;

    String email;

    String phone;

    String website;

    @ManyToOne
    UserEntity owner;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "school_cycle", joinColumns = @JoinColumn(name = "school_id"))
    @Column(name = "cycle")
    @Enumerated(EnumType.STRING)
    Set<Cycle> cycles = new HashSet<>();

}
