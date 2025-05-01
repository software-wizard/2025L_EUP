package pl.psi.Spells;

import pl.psi.creatures.Creature;

public abstract class Spell {
    String name;
    int spellLevel;
    int duration;

    public Spell(String name, int spellLevel, int duration) {
        this.name = name;
        this.spellLevel = spellLevel;
        this.duration = duration;
    }

    public abstract void cast(Creature targetCreature);
}

