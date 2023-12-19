package sn.hsl.notelabback.entities.enums;

import lombok.Getter;

public enum Speciality {
    S1("S1"), S2("S2"), S3("S3"), S4("S4"),
    L1("L1"), L2("L2"),
    T1("T1"), T2("T2"),

    MATH("Math"), BIOLOGIE("Biologie"),
    GENIE_LOGICIEL("Génie logiciel"),

    MEDCINE("Medcine"), PHARMACIE("Pharmacie"),

    ECONOMIE("Economie"),

    AUTRES("Spécialité pour élémentaire et collége");

    @Getter
    private String description;

    Speciality(String description) {
        this.description = description;
    }
}
