package pl.psi;

import pl.psi.creatures.Creature;

public class BuffField extends SpecialField {

    public BuffField() {
        super(Color.ORANGE, SpecialField.FieldName.BuffField);
    }

    @Override
    public void doSomething(Creature aCreature) {
        System.err.println("Err");
    }
}
