package sn.hsl.notelabback.entities.enums;

import lombok.Getter;

public enum Permissions {
    ALL_ACCESS("Toutes les permissions"),

    ADD_USER("Ajouter un utilisateur"),
    EDIT_USER("Modifier un utilisateur"),
    DELETE_USER("Supprimer un utilisateur"),
    READ_USERS("Consulter les utilisateurs"),

    ADD_CLASSROOM("Ajouter une classe"),
    EDIT_CLASSROOM("Modifier une classe "),
    DELETE_CLASSROOM("Supprimer une classe"),
    READ_CLASSROOMS("Consulter les classes");

    @Getter
    private String description;

    Permissions(String description) {
        this.description = description;
    }
}
