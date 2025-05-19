package pl.psi.Spells;

import lombok.Getter;
import pl.psi.creatures.CreatureStats;

@Getter
public class ActiveSpellEffect {
    private final Spell spell;
    private int remainingTurns;

    public ActiveSpellEffect(Spell spell, int duration) {
        this.spell = spell;
        this.remainingTurns = duration;
    }

    public void decreaseDuration() {
        remainingTurns--;
    }

    public boolean isExpired() {
        return remainingTurns <= 0;
    }

}
