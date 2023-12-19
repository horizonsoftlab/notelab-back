package sn.hsl.notelabback.entities.enums;

import lombok.Getter;

import java.util.Set;

public enum Cycle {
    PRIMAIRE("Primaire", Set.of(Level.CI, Level.CP, Level.CE1, Level.CE2, Level.CM1, Level.CM2)),
    COLLEGE("Collége", Set.of(Level.SIXIEME, Level.CINQIEME, Level.QUATRIEME, Level.TROISIEME)),
    LYCEE("Lycée", Set.of(Level.SECONDE, Level.PREMIERE, Level.TERMINAL)),
    SUPERIEURE("Etudes superieures", Set.of(Level.L1, Level.L2, Level.L3, Level.M1, Level.M2));

    @Getter
    private String description;
    @Getter
    private Set<Level> levels;

    Cycle(String description, Set<Level> levels) {
        this.description = description;
        this.levels = levels;
    }
}
