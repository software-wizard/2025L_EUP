package pl.psi.hero.skills;

import pl.psi.creatures.EconomyCreature;

public abstract class AbstractSkill {
    public SkillLevel level;
    public AbstractSkill() {
        level = SkillLevel.Basic;
    }
    public abstract void apply(EconomyCreature creature);
    public abstract SkillName getName();
    public abstract void upgrade();
    public SkillLevel getLevel() {return this.level;}
}
