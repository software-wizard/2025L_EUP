package pl.psi.map.buildings.bank;

import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.map.resources.Resources;

import java.util.Map;

public enum BankStatistics {
    CASTLE_1(
        Map.of(
            new Point(6,5), new Creature.Builder().statistic(CreatureStatistic.LICH).amount(10).build(),
            new Point(5,6), new Creature.Builder().statistic(CreatureStatistic.LICH).amount(10).build(),
            new Point(4,5), new Creature.Builder().statistic(CreatureStatistic.LICH).amount(10).build(),
            new Point(5,4), new Creature.Builder().statistic(CreatureStatistic.LICH).amount(10).build()),
            new Resources(0,0,0,0,0,0,0)
        ),
    CASTLE_2
        (Map.of(
            new Point(6,5), new Creature.Builder().statistic(CreatureStatistic.BLACK_KNIGHT).amount(10).build(),
            new Point(5,6), new Creature.Builder().statistic(CreatureStatistic.SKELETON).amount(10).build(),
            new Point(4,5), new Creature.Builder().statistic(CreatureStatistic.DREAD_KNIGHT).amount(10).build(),
            new Point(5,4), new Creature.Builder().statistic(CreatureStatistic.BONE_DRAGON).amount(10).build()),
            new Resources(0,0,0,0,0,0,0)
        );

    private final Map<Point, Creature> enemies;
    private final Resources prize;

    BankStatistics(final Map<Point, Creature> aEnemies, final Resources aPrize){
        enemies = aEnemies;
        prize = aPrize;
    }

    public Map<Point, Creature> getEnemies() {
        return enemies;
    }

    public Resources getPrize() {
        return prize;
    }

}
