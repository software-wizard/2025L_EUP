package pl.psi.Spells;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;



public class BuffSpell extends Spell{

    private final CreatureStats buffStats;

    public BuffSpell(String name, CreatureStats buffStats, int duration) {
        super(name, duration);
        this.buffStats = buffStats;
    }

    @Override
    public void cast(Creature targetCreature) {
        targetCreature.applyTemporaryBuff(buffStats, duration);   }


}
