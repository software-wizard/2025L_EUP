package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.hero.artifacts.Artifact;
import pl.psi.hero.artifacts.ArtifactType;
import pl.psi.map.MapObjectIf;
import pl.psi.map.resources.Resources;

class EcoBattleConverterTest {
    private final Statistics aStats = new Statistics(10, 10, 10, 10);

    @Test
    void shouldConvertCreaturesCorrectly() {
        final EconomyHero ecoHero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, new Resources(1000, 0, 0, 0, 0, 0, 0), aStats);
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature(factory.create(false, 1, 1));
        ecoHero.addCreature(factory.create(false, 2, 2));
        ecoHero.addCreature(factory.create(false, 3, 3));
        ecoHero.addCreature(factory.create(false, 4, 4));
        ecoHero.addCreature(factory.create(false, 5, 5));
        ecoHero.addCreature(factory.create(false, 6, 6));
        ecoHero.addCreature(factory.create(false, 7, 7));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert(ecoHero)
                .getCreatures();

        assertEquals(7, convertedCreatures.size());

        assertEquals("Skeleton", convertedCreatures.get(0)
                .getName());
        assertEquals(1, convertedCreatures.get(0)
                .getAmount());

        assertEquals("Walking Dead", convertedCreatures.get(1)
                .getName());
        assertEquals(2, convertedCreatures.get(1)
                .getAmount());

        assertEquals("Wight", convertedCreatures.get(2)
                .getName());
        assertEquals(3, convertedCreatures.get(2)
                .getAmount());

        assertEquals("Vampire", convertedCreatures.get(3)
                .getName());
        assertEquals(4, convertedCreatures.get(3)
                .getAmount());

        assertEquals("Lich", convertedCreatures.get(4)
                .getName());
        assertEquals(5, convertedCreatures.get(4)
                .getAmount());

        assertEquals("Black Knight", convertedCreatures.get(5)
                .getName());
        assertEquals(6, convertedCreatures.get(5)
                .getAmount());

        assertEquals("Bone Dragon", convertedCreatures.get(6)
                .getName());
        assertEquals(7, convertedCreatures.get(6)
                .getAmount());
    }

    @Test
    void shouldConvertArtifactEffectsToCreatureBonuses() {
        var hero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS,
                new Resources(100, 100, 100, 100, 100, 100, 100),
                new Statistics(1, 1, 1, 1));

        Artifact artifact = new Artifact(ArtifactType.SWORD_OF_HELLFIRE);
        hero.addArtifact(artifact);

        assertEquals(3, hero.getTotalStatistics().getAttack());
        assertEquals(4, hero.getTotalStatistics().getDefense());

        EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        var ecoCreature = factory.create(false, 1, 10);

        Creature baseCreature = new Creature.Builder()
                .statistic(ecoCreature.getStats())
                .amount(ecoCreature.getAmount())
                .build();

        Creature creatureWithBonuses = EcoBattleConverter.convertCreatureWithEffects(
                ecoCreature, hero
        );

        int expectedAttack = baseCreature.getAttack() + hero.getTotalStatistics().getAttack();
        int expectedDefense = baseCreature.getArmor() + hero.getTotalStatistics().getDefense();

        assertEquals(expectedAttack, creatureWithBonuses.getAttack());
        assertEquals(expectedDefense, creatureWithBonuses.getArmor());
    }

}