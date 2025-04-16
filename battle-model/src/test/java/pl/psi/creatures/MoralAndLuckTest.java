package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.Hero;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MoralAndLuckTest
{
    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    final CreatureStatisticIf stats = CreatureStats.builder()
            .name("Test Creature")
            .attack(10)
            .armor(0)
            .maxHp(100)
            .moveRange(0)
            .damage(NOT_IMPORTANT_DMG)
            .tier(1)
            .description("")
            .isUpgraded(false)
            .build();

//    MORAL

    @Test
    void creatureShouldSkipTurnWithNegativeMorale() {
        Hero hero = new Hero(List.of(), -3, 0);
        Creature creature = new Creature.Builder()
                .statistic(stats)
                .amount(1)
                .hero(hero)
                .build();

        int skipCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (creature.shouldSkipTurn()) {
                skipCount++;
            }
        }

        double skipChance = (double) skipCount / 10000;
        assertThat(skipChance).isBetween(0.24, 0.26); //25%
    }

    @Test
    void creatureShouldGetExtraTurnWithPositiveMorale() {
        Hero hero = new Hero(List.of(), 3, 0);
        Creature creature = new Creature.Builder()
                .statistic(stats)
                .amount(1)
                .hero(hero)
                .build();

        int extraTurnCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (creature.shouldGetExtraTurn()) {
                extraTurnCount++;
            }
        }

        double extraTurnChance = (double) extraTurnCount / 10000;
        assertThat(extraTurnChance).isBetween(0.115, 0.135);//12.5%
    }

    @Test
    void creatureShouldNotSkipTurnWithZeroMorale() {
        Hero hero = new Hero(List.of(), 0, 0);
        Creature creature = new Creature.Builder()
                .statistic(stats)
                .amount(1)
                .hero(hero)
                .build();

        int skipCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (creature.shouldSkipTurn()) {
                skipCount++;
            }
        }

        assertThat(skipCount).isEqualTo(0);
    }

    @Test
    void creatureShouldNotGetExtraTurnWithZeroMorale() {
        Hero hero = new Hero(List.of(), 0, 0);
        Creature creature = new Creature.Builder()
                .statistic(stats)
                .amount(1)
                .hero(hero)
                .build();

        int extraTurnCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (creature.shouldGetExtraTurn()) {
                extraTurnCount++;
            }
        }

        assertThat(extraTurnCount).isEqualTo(0);
    }

//    LUCK

    @Test
    void creatureShouldGetDoubleDamageWithPositiveLuck()
    {
        Hero hero = new Hero(List.of(), 0, 3);
        Creature creature = new Creature.Builder()
                .statistic(stats)
                .amount(1)
                .hero(hero)
                .build();
    }
}
