package pl.psi;

import pl.psi.creatures.Creature;

import static pl.psi.SpecialField.FieldName.FIRE_FIELD;

public class FireWall extends SpecialField {

    int duration = 2;
    int damage;

    public FireWall(int damage) {
        super(Color.ORANGE, FIRE_FIELD);
        this.duration = 2;
        this.damage = damage;
    }


    public void doSomething(Creature aCreature) {
        aCreature.applyDamage(aCreature, this.damage);
    }
}
