package sn.hsl.notelabback.entities.enums;

import lombok.Getter;

public enum Sexe {
    MASCULIN("Masculin"),
    FEMININ("Féminin");

    @Getter
    private String description;

    Sexe(String description) {
        this.description = description;
    }
}
