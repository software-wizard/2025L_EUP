package pl.psi.spells;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.psi.Hero;
import pl.psi.Spells.BuffSpell;
import pl.psi.Spells.DamageSpell;
import pl.psi.Spells.Spell;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class SpellTest {


    @Test
    void damagingSpellShouldApplyDamage(){

        final Creature c1 = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 10))
                        .attack(10)
                        .armor(5)
                        .build())
                .build();

        final Hero h1 = new Hero(List.of(c1), List.of());
        final Spell deafultDamageSpell = new DamageSpell("deafult", 1, 1);


        h1.apply(deafultDamageSpell,c1);

        assertThat(c1.getCurrentHp()).isBetween(80, 95);
    }

    @Test
    void buffSpellShouldAddStats(){
        final Creature c1 = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 10))
                        .attack(10)
                        .armor(5)
                        .build())
                .build();
        final Hero h1 = new Hero(List.of(c1), List.of());
        CreatureStats statBuff = CreatureStats.builder()
                .attack(5)
                .armor(0)
                .maxHp(0)
                .moveRange(0)
                .name("Buff")
                .description("Powerful boost")
                .tier(1)
                .damage(Range.closed(0, 0))
                .isUpgraded(false)
                .build();

        Spell buffSpell = new BuffSpell("", 1,1,  statBuff);
        h1.apply(buffSpell, c1);

        assertThat(c1.getAttack()).isEqualTo(15);

    }

    @Test
    void buffSpellShouldSubtractStats(){
        final Creature c1 = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 10))
                        .attack(10)
                        .armor(5)
                        .build())
                .build();
        final Hero h1 = new Hero(List.of(c1), List.of());
        CreatureStats statDebuff = CreatureStats.builder()
                .attack(-5)
                .armor(0)
                .maxHp(0)
                .moveRange(0)
                .name("")
                .description("")
                .tier(1)
                .damage(Range.closed(0, 0))
                .isUpgraded(false)
                .build();

        Spell debuffspell = new BuffSpell("", 1, 1, statDebuff);
        h1.apply(debuffspell, c1);

        assertThat(c1.getAttack()).isEqualTo(5);
    }

    @Test
    void statsShouldComeBackToOriginal(){
        final Creature attacker = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 10))
                        .attack(10)
                        .armor(5)
                        .build())
                .build();
        final Creature defender = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 10))
                        .attack(10)
                        .armor(5)
                        .build())
                .build();
        final Hero h1 = new Hero(List.of(attacker), List.of());
        CreatureStats statBuff = CreatureStats.builder()
                .attack(5)
                .armor(0)
                .maxHp(0)
                .moveRange(0)
                .name("Buff")
                .description("Powerful boost")
                .tier(1)
                .damage(Range.closed(0, 0))
                .isUpgraded(false)
                .build();

        Spell buffSpell = new BuffSpell("", 1, 2, statBuff);
        final TurnQueue turnQueue = new TurnQueue(List.of(attacker), List.of(defender));

        h1.apply(buffSpell, attacker);
        assertThat(attacker.getAttack()).isEqualTo(15);

        turnQueue.next();
        turnQueue.next();

        turnQueue.next();
        turnQueue.next();

        assertThat(attacker.getAttack()).isEqualTo(10);
    }

    @Test
    void creatureCanHaveMoreThanOneBuffSpell(){
        final Creature c1 = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 10))
                        .attack(10)
                        .armor(5)
                        .build())
                .build();
        final Hero h1 = new Hero(List.of(c1), List.of());

        CreatureStats statBuff = CreatureStats.builder()
                .attack(5)
                .armor(0)
                .maxHp(0)
                .moveRange(0)
                .name("Buff")
                .description("Powerful boost")
                .tier(1)
                .damage(Range.closed(0, 0))
                .isUpgraded(false)
                .build();

        Spell buffSpellOne = new BuffSpell("", 1,1,  statBuff);
        Spell buffSpellTwo = new BuffSpell("", 1,2,  statBuff);
        final TurnQueue turnQueue = new TurnQueue(List.of(c1), List.of());

        h1.apply(buffSpellOne, c1);
        h1.apply(buffSpellTwo, c1);

        assertThat(c1.getAttack()).isEqualTo(20);

        turnQueue.next();
        assertThat(c1.getActiveSpellEffects().size()).isEqualTo(1);
        assertThat(c1.getAttack()).isEqualTo(15);


        turnQueue.next();
        assertThat(c1.getActiveSpellEffects().size()).isEqualTo(0);
        assertThat(c1.getAttack()).isEqualTo(10);
    }



}
