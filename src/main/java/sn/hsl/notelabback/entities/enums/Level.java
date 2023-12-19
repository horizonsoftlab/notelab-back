package sn.hsl.notelabback.entities.enums;

import lombok.Getter;

public enum Level {
    CI("CI"), CP("CP"), CE1("CE1"), CE2("CE2"), CM1("CM1"), CM2("CM2"),
    SIXIEME("6E"), CINQIEME("5E"), QUATRIEME("4E"), TROISIEME("3E"),
    SECONDE("Seconde"), PREMIERE("Première"), TERMINAL("Terminal"),
    L1("Licence 1"), L2("Licence 2"), L3("Licence 3"), M1("MASTER 1"), M2("Master 2");

    @Getter
    private final String description;

//    @Getter
//    private final Cycle cycle;

    Level(String description) {
        this.description = description;
//        this.cycle = cycle;
    }
}
