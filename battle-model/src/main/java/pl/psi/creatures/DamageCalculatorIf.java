package pl.psi.creatures;

import pl.psi.Spells.Spell;

public interface DamageCalculatorIf
{
    int calculateDamage( Creature aAttacker, Creature aDefender );
    int calculateMagicDamage(Creature aDefender, Spell aSpell);
}
