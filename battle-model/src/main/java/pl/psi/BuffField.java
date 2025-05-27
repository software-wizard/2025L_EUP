package pl.psi;

import pl.psi.creatures.Creature;

public class BuffField extends SpecialField {

    public BuffField() {
        super(Color.ORANGE, FieldName.BUFF_FIELD);
    }

    @Override
    public void doSomething(Creature aCreature) {
        System.err.println("Err");
    }
}
