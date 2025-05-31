package pl.psi;

import pl.psi.Spells.BuffSpell;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

public class BuffField extends SpecialField {

    public BuffField() {
        super(Color.ORANGE, FieldName.BUFF_FIELD);
    }

    @Override
    public void doSomething(Creature aCreature) {
        BuffSpell buff = new BuffSpell(
                "buffField",
                1,
                3,
                CreatureStats.builder()
                        .attack(5)
                        .armor(10)
                        .maxHp(20)
                        .moveRange(1)
                        .name(aCreature.getName())
                        .description("temporary buff")
                        .build()
        );
        aCreature.applyTemporaryBuff(buff);
    }
}
