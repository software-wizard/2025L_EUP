package pl.psi;

import lombok.Getter;

public class Skill {
    @Getter
    private final String name;
    @Getter
    private final String type;
    @Getter
    private String level;

    public Skill(final String aName, final String aType, String aLevel) {
        name = aName;
        type = aType;
        level = aLevel;
        double attackBonus = 0;
        double defenseBonus = 0;

        if (name == "Offence") {
            if (level == "Basic") {
                attackBonus = 0.1;
            } else if (level == "Advanced") {
                attackBonus = 0.2;
            } else if (level == "Expert") {
                attackBonus = 0.3;
            } else {
                throw new IllegalArgumentException("Invalid level for Offence skill");
            }
        } else if (name == "Armorer") {
            if (level == "Basic") {
                defenseBonus = 0.1;
            } else if (level == "Advanced") {
                defenseBonus = 0.2;
            } else if (level == "Expert") {
                defenseBonus = 0.3;
            } else {
                throw new IllegalArgumentException("Invalid level for Armorer skill");
            }
        }
    }

}
