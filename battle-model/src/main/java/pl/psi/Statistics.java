package pl.psi;

import lombok.Getter;

public class Statistics {

    @Getter
    private int attack;
    @Getter
    private int defense;
    @Getter
    private int power;
    @Getter
    private int knowledge;

    public Statistics(int attack, int defense, int power, int knowledge) {
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        this.knowledge = knowledge;
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

    public void increase(Statistics stats) {
        this.attack += stats.getAttack();
        this.defense += stats.getDefense();
        this.power += stats.getPower();
        this.knowledge += stats.getKnowledge();
    }
}
