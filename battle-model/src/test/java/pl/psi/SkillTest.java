package pl.psi;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.psi.converter.EcoBattleConverter.convert;

import pl.psi.creatures.Creature;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.skills.ArmorerSkill;
import pl.psi.hero.skills.OffenceSkill;


public class SkillTest {

    @Test
    void shouldApplySkillsBuff():
    {
        EconomyHero attacker = new EconomyHero(new List<Creature>(Arrays.asList(new Creature(),new Creature())));
        EconomyHero defender = new EconomyHero(new List<Creature>(Arrays.asList(new Creature(),new Creature())));

        EconomyHero attacker1 = new EconomyHero(new List<Creature>(Arrays.asList(new Creature(),new Creature())));
        EconomyHero defender1 = new EconomyHero(new List<Creature>(Arrays.asList(new Creature(),new Creature())));

        EconomyHero attacker2 = new EconomyHero(new List<Creature>(Arrays.asList(new Creature(),new Creature())));
        EconomyHero defender2 = new EconomyHero(new List<Creature>(Arrays.asList(new Creature(),new Creature())));

        attacker.addSkill(new OffenceSkill("Basic"));
        defender1.addSkill(new ArmorerSkill("Basic"));

        Hero attackerBattle= convert(attacker);
        Hero defenderBattle= convert(defender);
        Hero attackerBattle1= convert(attacker1);
        Hero defenderBattle1= convert(defender1);
        Hero attackerBattle2= convert(attacker2);
        Hero defenderBattle2= convert(defender2);

//      Test attacker
        attackerBattle.attack(defender);
        assertThat(defenderBattle.getCurrentHp()).isEqualTo(50);
        assertThat(defenderBattle.getAmount()).isEqualTo(2);

//      Test defender
        attackerBattle1.attack(defenderBattle1);
        assertThat(defenderBattle1.getCurrentHp()).isEqualTo(55);
        assertThat(defenderBattle1.getAmount()).isEqualTo(2);

//      Control group
        attackerBattle2.attack(defender2);
        assertThat(defenderBattle2.getCurrentHp()).isEqualTo(60);
        assertThat(defenderBattle2.getAmount()).isEqualTo(2);
    }
}
