package pl.psi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.Statistics;
import pl.psi.map.buildings.town.BuildingType;
import pl.psi.map.buildings.town.Town;
import pl.psi.map.buildings.town.TownBuilding;
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
        buildAllPrerequisites(baseBuilding);

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
        buildAllPrerequisites(baseBuilding);

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
        buildAllPrerequisites(base);

        // WHEN
        town.build(base, hero);
        town.build(upgraded, hero);

        // THEN
        assertTrue(town.hasBuilt(upgraded));
    }

    @Test
    void shouldNotAllowBuildingWithoutPrerequisites() {
        // GIVEN
        UpgradeBuildings upgraded = UpgradeBuildings.CURSED_TEMPLE_UPGRADED;

        // THEN
        assertThrows(IllegalStateException.class, () -> town.build(upgraded, hero));
    }

    @Test
    void shouldSubtractResourcesFromHeroWhenBuilding() {
        // GIVEN
        UpgradeBuildings building = UpgradeBuildings.CURSED_TEMPLE;
        buildAllPrerequisites(building);
        int initialGold = hero.getResources().getGold();

        // WHEN
        town.build(building, hero);

        // THEN
        int expectedGold = initialGold - building.getCost().getGold();
        assertEquals(expectedGold, hero.getResources().getGold());
    }

    @Test
    void shouldNotAllowBuildingSameUpgradeTwice() {
        // GIVEN
        UpgradeBuildings building = UpgradeBuildings.CURSED_TEMPLE;
        buildAllPrerequisites(building);
        town.build(building, hero);

        // THEN
        assertThrows(IllegalStateException.class, () -> town.build(building, hero));
    }

    @Test
    void shouldAllowUpgradeAfterBuildingBase() {
        // GIVEN
        UpgradeBuildings base = UpgradeBuildings.CURSED_TEMPLE;
        UpgradeBuildings upgrade = UpgradeBuildings.CURSED_TEMPLE_UPGRADED;
        buildAllPrerequisites(base);

        // WHEN
        town.build(base, hero);
        town.build(upgrade, hero);

        // THEN
        assertTrue(town.hasBuilt(base));
        assertTrue(town.hasBuilt(upgrade));
    }

    @Test
    void shouldBuildTownHallThenCityHallThenCapitol() {
        // Build required buildings
        town.build(TownBuilding.TAVERN, hero);
        town.build(TownBuilding.TOWN_HALL, hero);
        town.build(TownBuilding.MARKETPLACE, hero);
        town.build(TownBuilding.BLACKSMITH, hero);
        town.build(TownBuilding.MAGE_GUILD_LVL_1, hero);
        town.build(TownBuilding.CITY_HALL, hero);
        town.build(TownBuilding.FORT, hero);
        town.build(TownBuilding.CITADEL, hero);
        town.build(TownBuilding.CASTLE, hero);
        town.build(TownBuilding.CAPITOL, hero);

        assertTrue(town.hasBuilt(TownBuilding.CAPITOL));
    }

    @Test
    void shouldGiveResourcesAfterBuildingResourceSilo() {
        // GIVEN

        TownBuilding market = TownBuilding.MARKETPLACE;
        TownBuilding resource = TownBuilding.RESOURCE_SILO;

        // Build required building
        town.build(market, hero);
        town.build(resource, hero);

        int initialOre = hero.getResources().getOre();
        int initialWood = hero.getResources().getWood();

        market.applyEffect(town, hero);
        resource.applyEffect(town, hero);

        // THEN
        assertTrue(hero.getResources().getWood() > initialWood);
        assertTrue(hero.getResources().getOre() > initialOre);
    }

    private void buildAllPrerequisites(BuildingType building) {
        for (BuildingType prereq : building.getPrerequisites()) {
            if (!town.hasBuilt(prereq)) {
                buildAllPrerequisites(prereq); // Recursive
                town.build(prereq, hero);
            }
        }
    }

}
