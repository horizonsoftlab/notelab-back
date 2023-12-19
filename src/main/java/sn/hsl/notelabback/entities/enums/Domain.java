package sn.hsl.notelabback.entities.enums;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public enum Domain {
    S("Serie S", Set.of(Speciality.S1, Speciality.S2, Speciality.S3, Speciality.S4)),
    L("Serie L", Set.of(Speciality.L1, Speciality.L2)),
    T("Serie T", Set.of(Speciality.T1, Speciality.T2)),
    G("Serie G", new HashSet<>()),
    SCIENCES_ET_TECHNOLOGIES("Sciecnces et Technologies", Set.of(Speciality.MATH, Speciality.GENIE_LOGICIEL, Speciality.BIOLOGIE)),
    SANTE("SANTE", Set.of(Speciality.MEDCINE, Speciality.PHARMACIE)),
    ECONOMIE_ET_GESTION("Economie et gestion", Set.of(Speciality.ECONOMIE)),
    AUTRES("Domaine pour élémentaire et collége", new HashSet<>());

    @Getter
    private String description;

    @Getter
    Set<Speciality> specialities;

    Domain(String description, Set<Speciality> specialities) {
        this.description = description;
        this.specialities = specialities;
    }
}
