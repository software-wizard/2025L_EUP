package pl.psi;

import pl.psi.creatures.Creature;

public class DmgField extends SpecialField {

    public DmgField() {
        super(Color.BROWN, FieldType.FIELD_GIVING_DMG);
    }

    @Override
    public void doSomething(Creature aCreature) {
        aCreature.applyDamage(aCreature, 20);
    }
}
