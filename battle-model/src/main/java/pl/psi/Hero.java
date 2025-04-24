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
    private final Statistics statistics;
    @Getter
    private final List<Creature> creatures;

    public Hero(String name, Statistics statistics, final List<Creature> aCreatures) {
        this.name = name;
        this.statistics = statistics;
        creatures = aCreatures;
    }

    public int getAttack() {
        return statistics.getAttack();
    }

    public int getDefense() {
        return statistics.getDefense();
    }

    public int getPower() {
        return statistics.getPower();
    }

    public int getKnowledge() {
        return statistics.getKnowledge();
    }

    public void increaseStatistics(Statistics bonusStats) {
        statistics.increase(bonusStats);
    }


}
