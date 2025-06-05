//package pl.psi.hero;
//
//import com.google.common.collect.Range;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import pl.psi.creatures.*;
//import pl.psi.hero.skills.ArmorerSkill;
//import pl.psi.hero.skills.OffenceSkill;
//import pl.psi.map.resources.Resources;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class EconomyHeroTest {
//    @Disabled
//    @Test
//    void testHeroSkillsAffectDamageCalculation() {
//        // Setup hero with skills
//        EconomyHero hero = new EconomyHero(
//                EconomyHero.Fraction.NECROPOLIS,
//                new Resources(1000, 1000, 1000, 1000, 1000, 1000, 1000),
//                new Statistics(5, 5, 0, 0)
//        );
//        hero.addSkill(new ArmorerSkill());
//        hero.addSkill(new OffenceSkill());
//
//        // Setup attacker and defender creatures
//        EconomyCreature attacker = new EconomyCreature.Builder()
//                .statistic(new CreatureStats("Attacker",10, 5, 5, 5, Range.closed(4,5), 5, "Attacker",false))
//                .calculator(new DefaultDamageCalculator())
//                .amount(10)
//                .build();
//
//        EconomyCreature defender = new EconomyCreature.Builder()
//                .statistic(new CreatureStats("Defender", 10,5, 5, 5, Range.closed(4,5), 5, "Defender",false ))
//                .calculator(new ReducedDamageCalculator(0.1f, 0.1f)) // Factors from skills
//                .amount(10)
//                .build();
//
//        // Apply damage
//        attacker.attack(defender);
//
//        // Verify damage reduction and bonus attack are applied
//        assertTrue(defender.getAmount() < 10, "Defender should lose units due to attack.");
//        assertTrue(defender.getCurrentHp() > 0, "Defender should still have some HP left.");
//    }
//}
