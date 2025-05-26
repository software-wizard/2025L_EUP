package pl.psi;

import pl.psi.creatures.Creature;

public class DmgField extends SpecialField {

    public DmgField() {
        super(Color.BROWN, FieldName.DMG_FIELD);
    }

    @Override
    public void doSomething(Creature aCreature) {
        aCreature.applyDamage(aCreature, 20);
    }
}
