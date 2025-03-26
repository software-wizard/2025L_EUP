package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.TurnQueue;
import pl.psi.Hero;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class CreatureTest {

    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Test
    void creatureShouldAttackProperly() {
        // given
        final CreatureStatisticIf angelStats = new CreatureStats(
            "Angel", 50, 0, 100, 0, Range.closed(10, 10), 1, "", false
        );
        final CreatureStatisticIf dragonStats = new CreatureStats(
            "Dragon", 0, 10, 100, 0, NOT_IMPORTANT_DMG, 1, "", false
        );
        
        final Creature angel = new Creature.Builder()
                .statistic(angelStats)
                .amount(1)
                .build();
        final Creature dragon = new Creature.Builder()
                .statistic(dragonStats)
                .amount(1)
                .build();
        // when
        angel.attack(dragon);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(70);
    }

    @Test
    void creatureShouldNotHealCreatureEvenHasLowerAttackThanDefenderArmor() {
        final CreatureStatisticIf angelStats = new CreatureStats(
            "Angel", 1, 0, 100, 0, NOT_IMPORTANT_DMG, 1, "", false
        );
        final CreatureStatisticIf dragonStats = new CreatureStats(
            "Dragon", 0, 10, 100, 0, NOT_IMPORTANT_DMG, 1, "", false
        );
        
        final Creature angel = new Creature.Builder()
                .statistic(angelStats)
                .amount(1)
                .build();
        final Creature dragon = new Creature.Builder()
                .statistic(dragonStats)
                .amount(1)
                .build();
        // when
        angel.attack(dragon);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void defenderShouldCounterAttack() {
        final CreatureStatisticIf attackerStats = new CreatureStats(
            "Attacker", 0, 10, 100, 0, NOT_IMPORTANT_DMG, 1, "", false
        );
        final CreatureStatisticIf defenderStats = new CreatureStats(
            "Defender", 10, 0, 100, 0, Range.closed(10, 10), 1, "", false
        );
        
        final Creature attacker = new Creature.Builder()
                .statistic(attackerStats)
                .amount(1)
                .build();
        final Creature defender = new Creature.Builder()
                .statistic(defenderStats)
                .amount(1)
                .build();
        // when
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void defenderShouldNotCounterAttackWhenIsDie() {
        final CreatureStatisticIf attackerStats = new CreatureStats(
            "Attacker", 1000, 10, 100, 0, NOT_IMPORTANT_DMG, 1, "", false
        );
        final CreatureStatisticIf defenderStats = new CreatureStats(
            "Defender", 20, 5, 100, 0, NOT_IMPORTANT_DMG, 1, "", false
        );
        
        final Creature attacker = new Creature.Builder()
                .statistic(attackerStats)
                .amount(1)
                .build();
        final Creature defender = new Creature.Builder()
                .statistic(defenderStats)
                .amount(1)
                .build();
        // when
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void defenderShouldCounterAttackOnlyOncePerTurn() {
        final CreatureStatisticIf attackerStats = new CreatureStats(
            "Attacker", 0, 0, 100, 0, NOT_IMPORTANT_DMG, 1, "", false
        );
        final CreatureStatisticIf defenderStats = new CreatureStats(
            "Defender", 0, 0, 100, 0, Range.closed(10, 10), 1, "", false
        );
        
        final Creature attacker = new Creature.Builder()
                .statistic(attackerStats)
                .amount(1)
                .build();
        final Creature defender = new Creature.Builder()
                .statistic(defenderStats)
                .amount(1)
                .build();

        // when
        attacker.attack(defender);
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void counterAttackCounterShouldResetAfterEndOfTurn() {
        final CreatureStatisticIf attackerStats = new CreatureStats(
            "Attacker", 0, 0, 100, 0, NOT_IMPORTANT_DMG, 1, "", false
        );
        final CreatureStatisticIf defenderStats = new CreatureStats(
            "Defender", 0, 0, 100, 0, Range.closed(10, 10), 1, "", false
        );
        
        final Creature attacker = new Creature.Builder()
                .statistic(attackerStats)
                .amount(1)
                .build();
        final Creature defender = new Creature.Builder()
                .statistic(defenderStats)
                .amount(1)
                .build();

        final TurnQueue turnQueue = new TurnQueue(List.of(attacker), List.of(defender));

        attacker.attack(defender);
        attacker.attack(defender);
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
        turnQueue.next();
        turnQueue.next();
        attacker.attack(defender);
        assertThat(attacker.getCurrentHp()).isEqualTo(80);
    }

    @Test
    void creatureShouldHealAfterEndOfTurn() {
        final CreatureStatisticIf attackerStats = new CreatureStats(
            "Attacker", 0, 0, 100, 0, Range.closed(10, 10), 1, "", false
        );
        final CreatureStatisticIf defenderStats = new CreatureStats(
            "Defender", 0, 0, 100, 0, NOT_IMPORTANT_DMG, 1, "", false
        );
        
        final Creature attacker = new Creature.Builder()
                .statistic(attackerStats)
                .amount(1)
                .build();
        final Creature selfHealAfterEndOfTurnCreature = new SelfHealAfterTurnCreature(
            new Creature.Builder()
                .statistic(defenderStats)
                .amount(1)
                .build()
        );

        final TurnQueue turnQueue =
                new TurnQueue(List.of(attacker), List.of(selfHealAfterEndOfTurnCreature));

        attacker.attack(selfHealAfterEndOfTurnCreature);
        assertThat(selfHealAfterEndOfTurnCreature.getCurrentHp()).isEqualTo(90);
        turnQueue.next();
        turnQueue.next();
        assertThat(selfHealAfterEndOfTurnCreature.getCurrentHp()).isEqualTo(100);
    }
}
