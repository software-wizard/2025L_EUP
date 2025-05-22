package pl.psi;

import pl.psi.creatures.Creature;

public class SpellField extends SpecialField {
    public SpellField() {
        super(Color.CYAN, FieldName.SpellField);
    }

    @Override
    public void doSomething(Creature aCreature) {
        System.err.print("CZAR@@@@@@@@@");
    }
}
