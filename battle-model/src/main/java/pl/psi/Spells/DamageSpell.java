package pl.psi.Spells;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatisticIf;

public class DamageSpell extends Spell{

    public DamageSpell(String name, int spellLevel, int duration) {
        super(name, spellLevel, duration);
    }

    @Override
    public void cast(Creature targetCreature) {
        targetCreature.applyMagicDamage(this);
    }


}
