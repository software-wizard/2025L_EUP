package pl.psi;

import pl.psi.creatures.Creature;

public class SpellField extends SpecialField {
    public SpellField(FieldType aFieldType) {
        super(Color.BLUE, aFieldType);
    }

    @Override
    public void doSomething(Creature aCreature) {
        System.err.print("CZAR@@@@@@@@@");
    }


}
