package pl.psi;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreatureTest {

    @Test
    void x() {
        Creature attacker = new Creature(10, 1, 5, 5, Range.closed(5, 5));
        Creature defender = new Creature(100, 10, 0, 5, Range.closed(5, 5));

        attacker.attack(defender);

        assertThat(defender.getCurrentHp()).isEqualTo(95);
    }

    @Test
    void y() {
        Creature attacker = new Creature(10, 21, 5, 5, Range.closed(5, 5));
        Creature defender = new Creature(100, 3, 0, 5, Range.closed(5, 5));

        attacker.attack(defender);

        assertThat(defender.getCurrentHp()).isEqualTo(95);
        assertThat(defender.getAmount()).isEqualTo(2);
    }

    @Test
    void z() {
        //given
        Creature attacker = new Creature(10, 42, 5, 5, Range.closed(5, 5));
        Creature defender = new Creature(100, 3, 0, 5, Range.closed(5, 5));

        //when
        attacker.attack(defender);

        //then
        assertThat(defender.getCurrentHp()).isEqualTo(90);
        assertThat(defender.getAmount()).isEqualTo(1);
    }

    @Test
    void aa() {
        Creature attacker = new Creature(10, 42, 5, 5, Range.closed(5, 5));
        Creature defender = new Creature(100, 10, 0, 5, Range.closed(5, 5));

        attacker.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(90);
        assertThat(defender.getAmount()).isEqualTo(8);

        attacker.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(80);
        assertThat(defender.getAmount()).isEqualTo(6);
    }

    @Test
    void zz() {
        Creature attacker = new Creature(10, 42, 15, 5, Range.closed(5, 5));
        Creature defender = new Creature(100, 10, 0, 5, Range.closed(5, 5));

        attacker.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(85);
        assertThat(defender.getAmount()).isEqualTo(7);
    }

    @Test
    void aaa(){
        Creature attacker = new Creature(10, 42, 1000, 5, Range.closed(5, 5));
        Creature defender = new Creature(100, 10, 0, 5, Range.closed(5, 5));

        attacker.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(60);
        assertThat(defender.getAmount()).isEqualTo(2);
    }

    @Test
    void zzz() {
        Creature attacker = new Creature(10, 42, 10, 5, Range.closed(5, 5));
        Creature defender = new Creature(100, 10, 0, 51, Range.closed(5, 5));
            attacker.setDamageCalculator(new IgnoreArmorDamageCalculator());

        attacker.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(85);
        assertThat(defender.getAmount()).isEqualTo(7);
    }

}