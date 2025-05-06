package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.EconomyNecropolisFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EconomyHeroTest
{

    private EconomyHero hero;

    @BeforeEach
    void init()
    {
        hero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 3000, aStats);
    }

    @Test
    void shouldThrowExceptionWhileHeroHas7CreatureAndYoTryToAddNextOne()
    {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );

        assertThrows( IllegalStateException.class, () -> hero.addCreature( factory.create( true, 1, 1 ) ) );
    }

    @Test
    void shouldThrowExceptionWhileYouTrySubstractMoreGoldThanHeroHas()
    {
        assertThrows( IllegalStateException.class, () -> hero.substractGold( 3001 ) );
    }
    @Test
    void shouldCorrectlyApplyArtifactBonusesToHeroStatistics() {
        Statistics baseStats = new Statistics(10, 10, 10, 10);
        Artifact artifact = new Artifact("Amulet of Glory", 5, 3, 2, 1);

        // Zastąp starego bohatera nowym z bazowymi statystykami
        hero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 3000, baseStats);
        hero.addArtifact(artifact);

        assertEquals(15, hero.getAttack());
        assertEquals(13, hero.getDefense());
        assertEquals(12, hero.getPower());
        assertEquals(11, hero.getKnowledge());

        Statistics total = hero.getTotalStatistics();
        assertEquals(15, total.getAttack());
        assertEquals(13, total.getDefense());
        assertEquals(12, total.getPower());
        assertEquals(11, total.getKnowledge());

        assertEquals(1, hero.getArtifacts().size());
        assertEquals(5, hero.getArtifacts().get(0).getBonuses().getAttack());
    }
}