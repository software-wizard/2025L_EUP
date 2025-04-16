package pl.psi.artifacts;

import pl.psi.Hero;

public class Artifact {
    private String name;
    private int attackBonus;
    private int defenseBonus;
    private int powerBonus;
    private int knowledgeBonus;

    Artifact(String name, int attackBonus, int defenseBonus, int powerBonus, int knowledgeBonus) {
        this.name = name;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.powerBonus = powerBonus;
        this.knowledgeBonus = knowledgeBonus;
    }
    public void apply(Hero heros) {
        heros.increaseAttack(attackBonus);
        heros.increaseDefense(defenseBonus);
        heros.increasePower(powerBonus);
        heros.increaseKnowledge(knowledgeBonus);
    }
}
