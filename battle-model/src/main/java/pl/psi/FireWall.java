package pl.psi;

import pl.psi.creatures.Creature;

public class FireWall extends SpellField {

    int duration=2;
    int damage;
    public FireWall(int damage){
        super(FieldType.TRIGGERED_BY_STEPPING);
        this.duration = 2;
        this.damage = damage;
    }


    public void doSomething(Creature aCreature) {
        aCreature.applyDamage(aCreature, this.damage);
    }
}
