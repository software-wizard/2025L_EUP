package pl.psi.Spells;

import pl.psi.creatures.Creature;

public class DamageSpell extends Spell{

    public DamageSpell(String name, double power, int duration) {
        super(name, power, duration);
    }

    @Override
    public void cast(Creature targetCreature) {
        final double hpToSubtract =  power* targetCreature.getMaxHp();
        int hp = (int) (targetCreature.getCurrentHp() - hpToSubtract);
        targetCreature.setCurrentHp(hp); //applyspelldamage
    }
}
