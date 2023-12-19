package sn.hsl.notelabback.entities.enums;

import lombok.Getter;

import java.util.Set;

public enum Role {
    ADMIN("Administrateur", Set.of(Permissions.ALL_ACCESS)),
    OWNER("Propriétaire", Set.of(Permissions.ALL_ACCESS)),
    TEACHER("Professeur", Set.of(Permissions.READ_USERS)),
    STUDENT("Eléve", Set.of(Permissions.READ_USERS)),
    CLASSROOM_MANAGER("Responsable de classe", Set.of(Permissions.READ_USERS)),
    CLASSROOM_SECRETARY("Responsable administratif de classe", Set.of(Permissions.READ_USERS));

    @Getter
    private final String description;

    @Getter
    private final Set<Permissions> permissions;

    Role(String description, Set<Permissions> permissions) {
        this.description = description;
        this.permissions = permissions;
    }
}
