package pl.psi;

import java.util.List;

import pl.psi.creatures.Creature;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero {
    private String name;
    @Getter
    private int attack;
    @Getter
    private int defense;
    @Getter
    private int power;
    @Getter
    private int knowledge;

    @Getter
    private final List<Creature> creatures;

    public Hero(String name, int attack, int defense, int power, int knowledge, final List<Creature> aCreatures) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        this.knowledge = knowledge;
        creatures = aCreatures;
    }
    public void increaseAttack(int value) {
        this.attack += value;
    }

    public void increaseDefense(int value) {
        this.defense += value;
    }

    public void increasePower(int value) {
        this.power += value;
    }

    public void increaseKnowledge(int value) {
        this.knowledge += value;
    }




}
