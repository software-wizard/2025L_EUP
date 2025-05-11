package pl.psi.map.buildings.bank;

import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.map.resources.Resources;

import java.util.Map;

public enum BankStatistics {
    CASTLE_0(
            Map.of(
                    new Point(6, 5), new Creature.Builder().statistic(CreatureStatistic.SKELETON).amount(20).build(),
                    new Point(5, 6), new Creature.Builder().statistic(CreatureStatistic.SKELETON).amount(20).build(),
                    new Point(4, 5), new Creature.Builder().statistic(CreatureStatistic.SKELETON).amount(20).build(),
                    new Point(5, 4), new Creature.Builder().statistic(CreatureStatistic.WALKING_DEAD).amount(10).build()
            ),
            new Resources(1000, 0, 0, 0, 0, 0, 0)
    ),
    CASTLE_1(
            Map.of(
                    new Point(6,5), new Creature.Builder().statistic(CreatureStatistic.LICH).amount(6).build(),
                    new Point(5,6), new Creature.Builder().statistic(CreatureStatistic.LICH).amount(6).build(),
                    new Point(4,5), new Creature.Builder().statistic(CreatureStatistic.WIGHT).amount(10).build(),
                    new Point(5,4), new Creature.Builder().statistic(CreatureStatistic.WRAITH).amount(6).build()
            ),
            new Resources(2000, 2, 2, 1, 1, 1, 1)
    ),
    CASTLE_2(
            Map.of(
                    new Point(6,5), new Creature.Builder().statistic(CreatureStatistic.BLACK_KNIGHT).amount(6).build(),
                    new Point(5,6), new Creature.Builder().statistic(CreatureStatistic.SKELETON).amount(40).build(),
                    new Point(4,5), new Creature.Builder().statistic(CreatureStatistic.DREAD_KNIGHT).amount(4).build(),
                    new Point(5,4), new Creature.Builder().statistic(CreatureStatistic.BONE_DRAGON).amount(4).build()
            ),
            new Resources(4000, 4, 3, 3, 3, 3, 2)
    ),
    CASTLE_4(
            Map.of(
                    new Point(6, 5), new Creature.Builder().statistic(CreatureStatistic.LICH).amount(15).build(),
                    new Point(5, 6), new Creature.Builder().statistic(CreatureStatistic.WRAITH).amount(10).build(),
                    new Point(4, 5), new Creature.Builder().statistic(CreatureStatistic.ZOMBIE).amount(20).build(),
                    new Point(5, 4), new Creature.Builder().statistic(CreatureStatistic.WIGHT).amount(15).build()
            ),
            new Resources(2000, 2, 2, 2, 2, 2, 1)
    ),
    CASTLE_5(
            Map.of(
                    new Point(6, 5), new Creature.Builder().statistic(CreatureStatistic.LICH).amount(20).build(),
                    new Point(5, 6), new Creature.Builder().statistic(CreatureStatistic.BONE_DRAGON).amount(5).build(),
                    new Point(4, 5), new Creature.Builder().statistic(CreatureStatistic.DREAD_KNIGHT).amount(8).build(),
                    new Point(5, 4), new Creature.Builder().statistic(CreatureStatistic.WRAITH).amount(15).build()
            ),
            new Resources(3000, 3, 3, 3, 3, 3, 2)
    ),

    CASTLE_6(
            Map.of(
                    new Point(6, 5), new Creature.Builder().statistic(CreatureStatistic.DREAD_KNIGHT).amount(10).build(),
                    new Point(5, 6), new Creature.Builder().statistic(CreatureStatistic.BONE_DRAGON).amount(10).build(),
                    new Point(4, 5), new Creature.Builder().statistic(CreatureStatistic.LICH).amount(25).build(),
                    new Point(5, 4), new Creature.Builder().statistic(CreatureStatistic.WRAITH).amount(20).build()
            ),
            new Resources(5000, 5, 5, 5, 5, 5, 3)
    ),;




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
