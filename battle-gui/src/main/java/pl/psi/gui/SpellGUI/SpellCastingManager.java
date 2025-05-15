package pl.psi.gui.SpellGUI;

import lombok.Getter;
import pl.psi.Spells.Spell;

public class SpellCastingManager {
    private boolean isSpellMode = false;
    @Getter
    private Spell selectedSpell;

    public void activate(Spell spell) {
        selectedSpell = spell;
        isSpellMode = true;
    }

    public void deactivate() {
        selectedSpell = null;
        isSpellMode = false;
    }

    public boolean isActive() {
        return isSpellMode;
    }

}
