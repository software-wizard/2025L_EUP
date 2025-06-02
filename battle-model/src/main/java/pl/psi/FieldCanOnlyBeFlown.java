package pl.psi;

import pl.psi.Exceptions.*;
import pl.psi.creatures.Creature;

public class FieldCanOnlyBeFlown extends SpecialField {

    public FieldCanOnlyBeFlown() {
        super(Color.YELLOW, FieldName.FIELD_CAN_ONLY_BE_FLOWN);
    }

    @Override
    public void doSomething(Creature aCreature) {
        if (!SpecialField.canFly(aCreature.getName())) {
            throw new CannotPassFieldException("You can't pass this field");
        }
    }
}
