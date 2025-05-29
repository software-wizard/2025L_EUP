package pl.psi.map.buildings.bank;

import pl.psi.economy.Point;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.map.resources.Resources;

import java.util.HashMap;
import java.util.Map;

public enum BankStatistics {

    CASTLE_0(createEnemies(Map.of(
            new Point(6, 5), new TierAmount(false, 1, 20),
            new Point(5, 6), new TierAmount(false, 1, 20),
            new Point(4, 5), new TierAmount(false, 1, 20),
            new Point(5, 4), new TierAmount(false, 2, 10)
    )), new Resources(1000, 0, 0, 0, 0, 0, 0)),

    CASTLE_1(createEnemies(Map.of(
            new Point(6, 5), new TierAmount(false, 5, 6),
            new Point(5, 6), new TierAmount(false, 5, 6),
            new Point(4, 5), new TierAmount(false, 4, 10),
            new Point(5, 4), new TierAmount(false, 4, 6)
    )), new Resources(2000, 2, 2, 1, 1, 1, 1)),

    CASTLE_2(createEnemies(Map.of(
            new Point(6, 5), new TierAmount(false, 6, 6),
            new Point(5, 6), new TierAmount(false, 1, 40),
            new Point(4, 5), new TierAmount(true, 6, 4),
            new Point(5, 4), new TierAmount(false, 7, 4)
    )), new Resources(4000, 4, 3, 3, 3, 3, 2)),

    CASTLE_4(createEnemies(Map.of(
            new Point(6, 5), new TierAmount(false, 5, 15),
            new Point(5, 6), new TierAmount(false, 4, 10),
            new Point(4, 5), new TierAmount(false, 2, 20),
            new Point(5, 4), new TierAmount(false, 4, 15)
    )), new Resources(2000, 2, 2, 2, 2, 2, 1)),

    CASTLE_5(createEnemies(Map.of(
            new Point(6, 5), new TierAmount(false, 5, 20),
            new Point(5, 6), new TierAmount(false, 7, 5),
            new Point(4, 5), new TierAmount(true, 6, 8),
            new Point(5, 4), new TierAmount(false, 4, 15)
    )), new Resources(3000, 3, 3, 3, 3, 3, 2)),

    CASTLE_6(createEnemies(Map.of(
            new Point(6, 5), new TierAmount(true, 6, 10),
            new Point(5, 6), new TierAmount(false, 7, 10),
            new Point(4, 5), new TierAmount(false, 5, 25),
            new Point(5, 4), new TierAmount(false, 4, 20)
    )), new Resources(5000, 5, 5, 5, 5, 5, 3));

    private final Map<Point, EconomyCreature> enemies;
    private final Resources prize;

    BankStatistics(final Map<Point, EconomyCreature> enemies, final Resources prize) {
        this.enemies = enemies;
        this.prize = prize;
    }

    public Map<Point, EconomyCreature> getEnemies() {
        return enemies;
    }

    public Resources getPrize() {
        return prize;
    }

    private static Map<Point, EconomyCreature> createEnemies(Map<Point, TierAmount> config) {
        EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        Map<Point, EconomyCreature> enemies = new HashMap<>();
        for (Map.Entry<Point, TierAmount> entry : config.entrySet()) {
            TierAmount ta = entry.getValue();
            enemies.put(entry.getKey(), factory.create(ta.upgraded, ta.tier, ta.amount));
        }
        return enemies;
    }

    private static class TierAmount {
        final boolean upgraded;
        final int tier;
        final int amount;

        TierAmount(boolean upgraded, int tier, int amount) {
            this.upgraded = upgraded;
            this.tier = tier;
            this.amount = amount;
        }
    }
}
