package pl.psi.artifacts;

import lombok.Getter;
import pl.psi.Hero;
import pl.psi.creatures.Statistics;

public class Artifact {
    private String name;
//    private int attackBonus;
//    private int defenseBonus;
//    private int powerBonus;
//    private int knowledgeBonus;
@Getter
private Statistics bonuses;
    Artifact(String name, int attackBonus, int defenseBonus, int powerBonus, int knowledgeBonus) {
        this.name = name;
        this.bonuses = new Statistics(attackBonus, defenseBonus, powerBonus, knowledgeBonus);
//        this.attackBonus = attackBonus;
//        this.defenseBonus = defenseBonus;
//        this.powerBonus = powerBonus;
//        this.knowledgeBonus = knowledgeBonus;
    }
    public void apply(Hero hero) {
        hero.increaseStatistics(bonuses);
    }
}
