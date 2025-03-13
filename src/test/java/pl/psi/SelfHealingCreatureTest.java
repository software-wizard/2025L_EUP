package pl.psi;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SelfHealingCreatureTest {


    @Test
    void withSelfHealingCreature() {
        Creature attacker = new Creature(10, 1, 5, 5, Range.closed(5, 5), 1);
        Creature defender = new SelfHealingCreature(new Creature(100, 10, 0, 5, Range.closed(5, 5), 1));
        GameEngine engine = new GameEngine(List.of(attacker), List.of(defender));

        engine.attack(defender);
        engine.pass();

        assertThat(defender.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void standardCreature() {
        Creature attacker = new Creature(10, 1, 5, 5, Range.closed(5, 5), 1);
        Creature defender = new Creature(100, 10, 0, 5, Range.closed(5, 5), 1);
        GameEngine engine = new GameEngine(List.of(attacker), List.of(defender));

        engine.attack(defender);
        engine.pass();

        assertThat(defender.getCurrentHp()).isEqualTo(95);
    }
}