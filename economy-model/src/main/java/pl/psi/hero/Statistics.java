package pl.psi.hero;

import com.google.j2objc.annotations.ObjectiveCName;
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

    public void increase(Statistics stats) {
        this.attack += stats.getAttack();
        this.defense += stats.getDefense();
        this.power += stats.getPower();
        this.knowledge += stats.getKnowledge();
    }

    @Override
    public String toString(){
        return "Attack: " + attack + " Defense: " + defense + " Power: " + power + " Knowledge: " + knowledge;
    }
}
