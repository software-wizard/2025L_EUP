package pl.psi.hero.skills;

import pl.psi.creatures.EconomyCreature;

public abstract class AbstractSkill {
    private final String level;
    public AbstractSkill(String aLevel) {
        level = aLevel;
    }
    public abstract void apply(EconomyCreature creature);
    public String getName(){
        return this.getClass().getName();
    }
}
