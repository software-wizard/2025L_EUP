package pl.psi.hero.skills;

import pl.psi.creatures.EconomyCreature;

public abstract class AbstractSkill {
    private final String name;
    private final String level;
    public AbstractSkill(String aName, String aLevel) {
        name = aName;
        level = aLevel;
    }
    public abstract void apply(EconomyCreature creature);
}
