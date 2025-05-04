package pl.psi.Spells;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;


@Getter
public class BuffSpell extends Spell{

    private final CreatureStats buffStats;


    public BuffSpell(String name, int spellLevel, int duration, CreatureStats buffStats ) {
        super(name, spellLevel,duration);
        this.buffStats = buffStats;
    }

    @Override
    public void cast(Creature targetCreature) {
        targetCreature.applyTemporaryBuff(this);   }

    @Override
    public void expire(Creature targetCreature) {
        targetCreature.comeBackToStatsBeforeBuff(this);

    }

}
