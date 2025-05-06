package pl.psi.hero;

import lombok.Getter;
//import pl.psi.Hero;
import pl.psi.hero.Statistics;

public class Artifact {
    private String name;
    @Getter
    private Statistics bonuses;

    Artifact(String name, int attackBonus, int defenseBonus, int powerBonus, int knowledgeBonus) {
        this.name = name;
        this.bonuses = new Statistics(attackBonus, defenseBonus, powerBonus, knowledgeBonus);
    }

}
