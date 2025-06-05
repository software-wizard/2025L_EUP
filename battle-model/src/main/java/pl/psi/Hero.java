package pl.psi;

import java.util.List;

import pl.psi.Spells.ActiveSpellEffect;
import pl.psi.Spells.Spell;
import pl.psi.creatures.Creature;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero {
    @Getter
    private final List< Creature > creatures;
    @Getter
    private List<Spell> spells;

    public Hero(final List< Creature > aCreatures, List<Spell> aSpells)
    {
        creatures = aCreatures;
        spells = new java.util.ArrayList<>(aSpells);
    }

    public void apply(Spell s, Creature c) {
        s.cast(c);
    }

    public void addSpell(Spell spell) {
        spells.add(spell);
    }

    public void removeSpell(Spell spell) {
        spells.remove(spell);
    }

    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }
}
