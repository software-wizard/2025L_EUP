package pl.psi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.Statistics;
import pl.psi.map.buildings.Castle;
import pl.psi.creatures.UpgradeBuildings;
import pl.psi.map.resources.Resources;

import static org.junit.jupiter.api.Assertions.*;

public class CastleUpgradeTest {

    private Castle castle;
    private EconomyHero hero;
    private Resources resources;

    @BeforeEach
    void init() {
        castle = new Castle();
        resources = new Resources(100000,1000,1000,1000,1000,1000,1000);
        Statistics aStats = new Statistics(10, 10, 10, 10);
        hero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS,resources, aStats);
    }

    @Test
    void shouldOnlyUnlockBaseCreatureAfterBuildingBaseBuilding() {
        // GIVEN
        UpgradeBuildings baseBuilding = UpgradeBuildings.CURSED_TEMPLE;

        // WHEN
        castle.build(baseBuilding, hero);

        // THEN
        assertTrue(castle.hasBuilt(baseBuilding));
        assertFalse(castle.hasBuilt(UpgradeBuildings.CURSED_TEMPLE_UPGRADED));
    }

    @Test
    void shouldNotAllowUpgradedCreatureWithoutUpgradeBuilding() {
        // GIVEN
        UpgradeBuildings baseBuilding = UpgradeBuildings.CURSED_TEMPLE;

        // WHEN
        castle.build(baseBuilding, hero);

        // THEN
        assertTrue(castle.hasBuilt(baseBuilding));
        assertFalse(castle.hasBuilt(UpgradeBuildings.CURSED_TEMPLE_UPGRADED));
    }

    @Test
    void shouldUnlockUpgradedCreatureAfterBuildingUpgrade() {
        // GIVEN
        UpgradeBuildings base = UpgradeBuildings.CURSED_TEMPLE;
        UpgradeBuildings upgraded = UpgradeBuildings.CURSED_TEMPLE_UPGRADED;

        // WHEN
        castle.build(base, hero);
        castle.build(upgraded, hero);

        // THEN
        assertTrue(castle.hasBuilt(upgraded));
    }

    @Test
    void shouldMapCorrectlyFromCreatureToBuilding() {
        // GIVEN
        CreatureStatistic skeleton = CreatureStatistic.SKELETON;
        CreatureStatistic skeletonWarrior = CreatureStatistic.SKELETON_WARRIOR;

        // WHEN
        var baseBuilding = UpgradeBuildings.getBuildingForCreature(skeleton);
        var upgradeBuilding = UpgradeBuildings.getBuildingForCreature(skeletonWarrior);

        // THEN
        assertTrue(baseBuilding.isPresent());
        assertEquals(UpgradeBuildings.CURSED_TEMPLE, baseBuilding.get());

        assertTrue(upgradeBuilding.isPresent());
        assertEquals(UpgradeBuildings.CURSED_TEMPLE_UPGRADED, upgradeBuilding.get());
    }
}
