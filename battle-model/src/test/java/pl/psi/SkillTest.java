package pl.psi;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import pl.psi.creatures.Creature;

import pl.psi.creatures.Skill;


public class SkillTest {

    @Test
    void shouldApplySkillsBuff():
    {
        Hero attacker = new Hero(new List<Creature>(Arrays.asList(new Creature(),new Creature())),new List<Skill>(Arrays.asList(new Skill("Offence","Basic"))));
        Hero defender = new Hero(new List<Creature>(Arrays.asList(new Creature(),new Creature())),new List<Skill>(Arrays.asList(new Skill("Armorer","Basic"))));

        attacker.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(60);
        assertThat(defender.getAmount()).isEqualTo(2);
    }
}
