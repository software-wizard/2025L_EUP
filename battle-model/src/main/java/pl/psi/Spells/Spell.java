package pl.psi.Spells;

import lombok.Getter;
import pl.psi.creatures.Creature;

public abstract class Spell {
    String name;
    @Getter
    int spellLevel;
    @Getter
    int duration;

    public Spell(String name, int spellLevel, int duration) {
        this.name = name;
        this.spellLevel = spellLevel;
        this.duration = duration;
    }

    public abstract void cast(Creature targetCreature);

}

