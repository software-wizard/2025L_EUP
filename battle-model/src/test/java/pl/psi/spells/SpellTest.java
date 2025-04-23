package pl.psi.spells;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.Hero;
import pl.psi.Spells.BuffSpell;
import pl.psi.Spells.DamageSpell;
import pl.psi.Spells.DebuffSpell;
import pl.psi.Spells.Spell;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

        final Hero h1 = new Hero(List.of(c1));
        final Spell deafultDamageSpell = new DamageSpell("deafult", 0.2, 1);


        h1.apply(deafultDamageSpell,c1);

        assertThat(c1.getCurrentHp()).isEqualTo(80);
    }

    @Test
    void buffSpellShouldAddAttack(){
        final Creature c1 = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 10))
                        .attack(10)
                        .armor(5)
                        .build())
                .build();
        final Hero h1 = new Hero(List.of(c1));
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

        Spell buffSpell = new BuffSpell("", statBuff, 1);
        h1.apply(buffSpell, c1);

        assertThat(c1.getAttack()).isEqualTo(15);

    }

    @Test
    void debuffSpellSchouldSubtrackAttack(){
        final Creature c1 = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 10))
                        .attack(10)
                        .armor(5)
                        .build())
                .build();
        final Hero h1 = new Hero(List.of(c1));
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

        Spell debuffspell = new DebuffSpell("", statDebuff, 1);
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
        final Hero h1 = new Hero(List.of(attacker));
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

        Spell buffSpell = new BuffSpell("", statBuff, 2);
        h1.apply(buffSpell, attacker);
        final TurnQueue turnQueue = new TurnQueue(List.of(attacker), List.of(defender));

        assertThat(attacker.getAttack()).isEqualTo(15);

        turnQueue.next();

        assertThat(attacker.getBuffDuration()).isEqualTo(1);
//        turnQueue.next();
//        turnQueue.next();
//
//        assertThat(attacker.getAttack()).isEqualTo(10);
    }



}
