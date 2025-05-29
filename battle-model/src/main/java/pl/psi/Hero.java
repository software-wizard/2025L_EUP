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

    public Hero( final List< Creature > aCreatures)
    {
        creatures = aCreatures;
    }

    public void apply(Spell s, Creature c) {
        s.cast(c);    }

    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }
}
