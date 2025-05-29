package pl.psi.hero.skills;

public abstract class AbstractSkill {
    public SkillLevel level;
    public AbstractSkill() {
        level = SkillLevel.BASIC;
    }
    public abstract SkillName getName();
    public abstract void upgrade();
    public SkillLevel getLevel() {return this.level;}
    public abstract float getFactor();
}
