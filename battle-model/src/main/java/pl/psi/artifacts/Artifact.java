package pl.psi.artifacts;

import lombok.Getter;
import pl.psi.Hero;
import pl.psi.Statistics;

public class Artifact {
    private String name;
    @Getter
    private Statistics bonuses;

    Artifact(String name, int attackBonus, int defenseBonus, int powerBonus, int knowledgeBonus) {
        this.name = name;
        this.bonuses = new Statistics(attackBonus, defenseBonus, powerBonus, knowledgeBonus);
    }

    public void apply(Hero hero) {
        hero.increaseStatistics(bonuses );
    }
}
