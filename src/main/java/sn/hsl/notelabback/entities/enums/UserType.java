package sn.hsl.notelabback.entities.enums;

import lombok.Getter;

public enum UserType {
    ADMIN("Administrateur"),
    OWNER("Propriétaire"),
    TEACHER("Professeur"),
    STUDENT("Eléve"),
    CLASSROOM_MANAGER("Responsable de classe"),
    CLASSROOM_SECRETARY("Responsable administratif de classe");

    @Getter
    private String description;

    UserType(String description) {
        this.description = description;
    }
}
