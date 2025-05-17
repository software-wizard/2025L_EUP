package pl.psi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.Statistics;
import pl.psi.map.buildings.town.Town;
import pl.psi.map.buildings.town.UpgradeBuildings;
import pl.psi.map.resources.Resources;

import static org.junit.jupiter.api.Assertions.*;

public class TownUpgradeTest {

    private Town town;
    private EconomyHero hero;
    private Resources resources;

    @BeforeEach
    void init() {
        town = new Town();
        resources = new Resources(100000,1000,1000,1000,1000,1000,1000);
        Statistics aStats = new Statistics(10, 10, 10, 10);
        hero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS,resources, aStats);
    }

    @Test
    void shouldOnlyUnlockBaseCreatureAfterBuildingBaseBuilding() {
        // GIVEN
        UpgradeBuildings baseBuilding = UpgradeBuildings.CURSED_TEMPLE;

        // WHEN
        town.build(baseBuilding, hero);

        // THEN
        assertTrue(town.hasBuilt(baseBuilding));
        assertFalse(town.hasBuilt(UpgradeBuildings.CURSED_TEMPLE_UPGRADED));
    }

    @Test
    void shouldNotAllowUpgradedCreatureWithoutUpgradeBuilding() {
        // GIVEN
        UpgradeBuildings baseBuilding = UpgradeBuildings.CURSED_TEMPLE;

        // WHEN
        town.build(baseBuilding, hero);

        // THEN
        assertTrue(town.hasBuilt(baseBuilding));
        assertFalse(town.hasBuilt(UpgradeBuildings.CURSED_TEMPLE_UPGRADED));
    }

    @Test
    void shouldUnlockUpgradedCreatureAfterBuildingUpgrade() {
        // GIVEN
        UpgradeBuildings base = UpgradeBuildings.CURSED_TEMPLE;
        UpgradeBuildings upgraded = UpgradeBuildings.CURSED_TEMPLE_UPGRADED;

        // WHEN
        town.build(base, hero);
        town.build(upgraded, hero);

        // THEN
        assertTrue(town.hasBuilt(upgraded));
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
