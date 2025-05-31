package pl.psi;

import pl.psi.Spells.BuffSpell;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

public class DebuffField extends SpecialField {

    public DebuffField() {
        super(Color.GRAY, FieldName.DEBUFF_FIELD);
    }

    @Override
    public void doSomething(Creature aCreature) {
        BuffSpell buff = new BuffSpell(
                "debuffField",
                1,
                3,
                CreatureStats.builder()
                        .attack(-5)
                        .armor(-10)
                        .maxHp(-20)
                        .moveRange(-1)
                        .name(aCreature.getName())
                        .description("temporary debuff")
                        .build()
        );
        aCreature.applyTemporaryBuff(buff);
    }
}
