package pl.psi.Spells;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatisticIf;

@Getter
public abstract class Spell {

    String name;
    int spellLevel;
    int duration;

    public Spell(String name, int spellLevel) {
        this.name = name;
        this.spellLevel = spellLevel;
    }

    public Spell(String name, int spellLevel, int duration ) {
        this.name = name;
        this.duration = duration;
        this.spellLevel = spellLevel;
    }

    public abstract void cast(Creature targetCreature);

    public CreatureStatisticIf modifyStats(CreatureStatisticIf baseStats) {
        return baseStats;
    }
}

