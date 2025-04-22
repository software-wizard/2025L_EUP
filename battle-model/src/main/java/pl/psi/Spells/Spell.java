package pl.psi.Spells;

import pl.psi.creatures.Creature;

public abstract class Spell {
    String name;
    double power;
    int duration;

    public Spell(String name, double power, int duration) {
        this.name = name;
        this.power = power;
        this.duration = duration;
    }
    public Spell(String name,  int duration) {
        this.name = name;
        this.duration = duration;
    }

    public abstract void cast(Creature targetCreature);
}

