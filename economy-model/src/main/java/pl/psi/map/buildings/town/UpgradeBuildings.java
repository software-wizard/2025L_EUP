package pl.psi.map.buildings.town;

import pl.psi.creatures.CreatureStatistic;
import pl.psi.hero.EconomyHero;
import pl.psi.map.resources.Resources;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public enum UpgradeBuildings implements BuildingType {
    CURSED_TEMPLE(Set.of(TownBuilding.FORT), new Resources(400, 5, 5, 0, 0, 0, 0), CreatureStatistic.SKELETON, CreatureStatistic.SKELETON_WARRIOR),
    CURSED_TEMPLE_UPGRADED(Set.of(CURSED_TEMPLE), new Resources(1000, 5, 5, 0, 0, 0, 0), CreatureStatistic.SKELETON, CreatureStatistic.SKELETON_WARRIOR),

    GRAVEYARD(Set.of(CURSED_TEMPLE), new Resources(1000, 0, 5, 0, 0, 0, 0), CreatureStatistic.WALKING_DEAD, CreatureStatistic.ZOMBIE),
    GRAVEYARD_UPGRADED(Set.of(GRAVEYARD), new Resources(1000, 5, 5, 0, 0, 0, 0), CreatureStatistic.WALKING_DEAD, CreatureStatistic.ZOMBIE),

    TOMB_OF_SOULS(Set.of(GRAVEYARD), new Resources(1500, 5, 5, 0, 0, 0, 0), CreatureStatistic.WIGHT, CreatureStatistic.WRAITH),
    TOMB_OF_SOULS_UPGRADED(Set.of(TOMB_OF_SOULS), new Resources(1500, 0, 0, 4, 0, 0, 0), CreatureStatistic.WIGHT, CreatureStatistic.WRAITH),

    ESTATE(Set.of(GRAVEYARD), new Resources(2000, 5, 5, 0, 0, 0, 0), CreatureStatistic.VAMPIRE, CreatureStatistic.VAMPIRE_LORD),
    ESTATE_UPGRADED(Set.of(ESTATE, TownBuilding.NECROMANCY_AMPLIFIER), new Resources(2000, 10, 0, 4, 0, 10, 10), CreatureStatistic.VAMPIRE, CreatureStatistic.VAMPIRE_LORD),

    MAUSOLEUM(Set.of(GRAVEYARD), new Resources(2000, 0, 4, 4, 4, 0, 0), CreatureStatistic.LICH, CreatureStatistic.POWER_LICH),
    MAUSOLEUM_UPGRADED(Set.of(MAUSOLEUM), new Resources(2000, 0, 4, 4, 4, 0, 0), CreatureStatistic.LICH, CreatureStatistic.POWER_LICH),

    HALL_OF_DARKNESS(Set.of(ESTATE,MAUSOLEUM), new Resources(6000, 10, 10, 4, 0, 0, 0), CreatureStatistic.BLACK_KNIGHT, CreatureStatistic.DREAD_KNIGHT),
    HALL_OF_DARKNESS_UPGRADED(Set.of(HALL_OF_DARKNESS), new Resources(3000, 0, 5, 2, 2, 2, 2), CreatureStatistic.BLACK_KNIGHT, CreatureStatistic.DREAD_KNIGHT),

    DRAGON_VAULT(Set.of(HALL_OF_DARKNESS), new Resources(10000, 5, 5, 5, 5, 5, 0), CreatureStatistic.BONE_DRAGON, CreatureStatistic.GHOST_DRAGON),
    DRAGON_VAULT_UPGRADED(Set.of(DRAGON_VAULT), new Resources(15000, 5, 5, 20, 0, 0, 0), CreatureStatistic.BONE_DRAGON, CreatureStatistic.GHOST_DRAGON);

    private final Set<BuildingType> prerequisites;
    private final Resources cost;
    private final CreatureStatistic baseCreature;
    private final CreatureStatistic upgradedCreature;

    UpgradeBuildings(Set<BuildingType> prerequisites, Resources cost,
                     CreatureStatistic baseCreature, CreatureStatistic upgradedCreature) {
        this.prerequisites = prerequisites;
        this.cost = cost;
        this.baseCreature = baseCreature;
        this.upgradedCreature = upgradedCreature;
    }

    public Set<BuildingType> getPrerequisites() {
        return prerequisites;
    }

    @Override
    public void registerInTown(Town town) {
        town.addUpgradeBuilding(this);
    }

    @Override
    public void applyEffect(Town town, EconomyHero hero) {
    }
    public Resources getCost() {
        return cost;
    }

    public CreatureStatistic getBaseCreature() {
        return baseCreature;
    }

    public CreatureStatistic getUpgradedCreature() {
        return upgradedCreature;
    }

    @Override
    public boolean isBuiltIn(Town town) {
        return town.hasBuilt(this);
    }

    @Override
    public void buildIn(Town town, EconomyHero hero) {
        town.buildBuilding(this, hero);
    }

    public static Optional<UpgradeBuildings> getBuildingForCreature(CreatureStatistic creature) {
        return Arrays.stream(values())
                .filter(b -> b.getBaseCreature() == creature || b.getUpgradedCreature() == creature)
                .filter(b -> {
                    if (b.getBaseCreature() == creature && b.getPrerequisites().isEmpty()) return true;
                    if (b.getUpgradedCreature() == creature && !b.getPrerequisites().isEmpty()) return true;
                    return false;
                })
                .findFirst();
    }
}
