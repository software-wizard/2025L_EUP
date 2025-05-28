package pl.psi;

import com.google.common.collect.Range;
import pl.psi.Spells.BuffSpell;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

public class DebuffingField extends SpecialField {


    protected DebuffingField() {super(Color.YELLOW, FieldName.DebuffingField);
    }

    @Override
    public void doSomething(Creature aCreature) {
        aCreature.applyTemporaryBuff(new BuffSpell("ez", 2, 3, new CreatureStats.builder()
                .maxHp(100)
                .build())
                .build());
    }
}
