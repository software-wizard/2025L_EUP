package pl.psi.hero.skills;

import pl.psi.creatures.EconomyCreature;

public abstract class AbstractSkill {
    public String level;
    public AbstractSkill() {
        level = "Basic";
    }
    public abstract void apply(EconomyCreature creature);
    public abstract String getName();
    public abstract void upgrade();
    public String getLevel() {return this.level;}
}
