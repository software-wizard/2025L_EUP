package pl.psi;

import pl.psi.creatures.Creature;

public class SpellField extends SpecialField {
    public SpellField() {
        super(Color.CYAN);
    }

    @Override
    public void doSomething(Creature aCreature) {
        System.err.print("CZAR@@@@@@@@@");
    }

    @Override
    public String getNameOfField() {
        return "SpellField";
    }
}
