package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.Hero;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MoralAndLuckTest
{
    private static final Range<Integer> DMG = Range.closed(0, 0);

    final CreatureStatisticIf stats = CreatureStats.builder()
            .name("Test Creature")
            .attack(10)
            .armor(0)
            .maxHp(100)
            .moveRange(0)
            .damage(DMG)
            .tier(1)
            .description("")
            .isUpgraded(false)
            .build();

//    MORAL

    @Test
    void creatureShouldSkipTurnWithNegativeMorale() {
        Hero hero = new Hero(List.of(), -3, 0);
        MoraleCreature moraleCreature = new MoraleCreature(
                new Creature.Builder()
                        .statistic(stats)
                        .amount(1)
                        .build(),
                hero);

        int skipCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (moraleCreature.shouldSkipTurn()) {
                skipCount++;
            }
        }

        double skipChance = (double) skipCount / 10000;
        assertThat(skipChance).isBetween(0.24, 0.26); //25%
    }

    @Test
    void creatureShouldGetExtraTurnWithPositiveMorale() {
        Hero hero = new Hero(List.of(), 3, 0);
        MoraleCreature moraleCreature = new MoraleCreature(
                new Creature.Builder()
                        .statistic(stats)
                        .amount(1)
                        .build(),
                hero);

        int extraTurnCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (moraleCreature.shouldGetExtraTurn()) {
                extraTurnCount++;
            }
        }

        double extraTurnChance = (double) extraTurnCount / 10000;
        assertThat(extraTurnChance).isBetween(0.115, 0.135);//12.5%
    }

    @Test
    void creatureShouldNotSkipTurnWithZeroMorale() {
        Hero hero = new Hero(List.of(), 0, 0);
        MoraleCreature moraleCreature = new MoraleCreature(
                new Creature.Builder()
                        .statistic(stats)
                        .amount(1)
                        .build(),
                hero);

        int skipCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (moraleCreature.shouldSkipTurn()) {
                skipCount++;
            }
        }

        assertThat(skipCount).isEqualTo(0);
    }

    @Test
    void creatureShouldNotGetExtraTurnWithZeroMorale() {
        Hero hero = new Hero(List.of(), 0, 0);
        MoraleCreature moraleCreature = new MoraleCreature(
                new Creature.Builder()
                        .statistic(stats)
                        .amount(1)
                        .build(),
                hero);

        int extraTurnCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (moraleCreature.shouldGetExtraTurn()) {
                extraTurnCount++;
            }
        }

        assertThat(extraTurnCount).isEqualTo(0);
    }

//    LUCK

    @Test
    void creatureShouldGetDoubleDamageWithPositiveLuck() {
        Hero hero = new Hero(List.of(), 0, 3);
        LuckyCreature luckyCreature = new LuckyCreature(
                new Creature.Builder()
                        .statistic(stats)
                        .amount(1)
                        .build(),
                hero);

        int doubleDamageCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (luckyCreature.shouldDoubleDamage()) {
                doubleDamageCount++;
            }
        }

        double doubleDamageChance = (double) doubleDamageCount / 10000;
        assertThat(doubleDamageChance).isBetween(0.24, 0.26); // 25%
    }

    @Test
    void creatureShouldNotGetDoubleDamageWithZeroLuck() {
        Hero hero = new Hero(List.of(), 0, 0);
        LuckyCreature luckyCreature = new LuckyCreature(
                new Creature.Builder()
                        .statistic(stats)
                        .amount(1)
                        .build(),
                hero);

        int doubleDamageCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (luckyCreature.shouldDoubleDamage()) {
                doubleDamageCount++;
            }
        }

        assertThat(doubleDamageCount).isEqualTo(0);
    }

    @Test //dokonczyc
    void CreatureShouldDoubleDamageWhenLucky() {
        final CreatureStatisticIf attackerStats = CreatureStats.builder()
                .name("Attacker")
                .attack(10)
                .armor(0)
                .maxHp(100)
                .moveRange(0)
                .damage(Range.closed(10, 10))
                .tier(1)
                .description("")
                .isUpgraded(false)
                .build();

        final CreatureStatisticIf defenderStats = CreatureStats.builder()
                .name("Defender")
                .attack(0)
                .armor(0)
                .maxHp(100)
                .moveRange(0)
                .damage(DMG)
                .tier(1)
                .description("")
                .isUpgraded(false)
                .build();

        Hero attackerHero = new Hero(List.of(), 0, 3);
        Hero defenderHero = new Hero(List.of(), 0, 0);

        LuckyCreature attacker = new LuckyCreature(
                new Creature.Builder()
                        .statistic(attackerStats)
                        .amount(1)
                        .build(),
                attackerHero);

        Creature defender = new Creature.Builder()
                .statistic(defenderStats)
                .amount(1)
                .hero(defenderHero)
                .build();

//        attacker.attack(defender);
//        assertThat(defender.getCurrentHp()).isEqualTo(80);
    }
}
