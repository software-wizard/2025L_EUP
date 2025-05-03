package pl.psi.creatures;

import pl.psi.creatures.CreatureStatistic;
import pl.psi.map.resources.Resources;

import java.util.Arrays;
import java.util.Optional;

public enum UpgradeBuildings {
    CURSED_TEMPLE(new Resources(400, 5, 5, 0, 0, 0, 0), null, CreatureStatistic.SKELETON, CreatureStatistic.SKELETON_WARRIOR),
    CURSED_TEMPLE_UPGRADED(new Resources(1000, 5, 5, 0, 0, 0, 0), CURSED_TEMPLE, CreatureStatistic.SKELETON, CreatureStatistic.SKELETON_WARRIOR),

    GRAVEYARD(new Resources(1000, 0, 5, 0, 0, 0, 0), null, CreatureStatistic.WALKING_DEAD, CreatureStatistic.ZOMBIE),
    GRAVEYARD_UPGRADED(new Resources(1000, 5, 5, 0, 0, 0, 0), GRAVEYARD, CreatureStatistic.WALKING_DEAD, CreatureStatistic.ZOMBIE),

    TOMB_OF_SOULS(new Resources(1500, 5, 5, 0, 0, 0, 0), null, CreatureStatistic.WIGHT, CreatureStatistic.WRAITH),
    TOMB_OF_SOULS_UPGRADED(new Resources(1500, 0, 0, 4, 0, 0, 0), TOMB_OF_SOULS, CreatureStatistic.WIGHT, CreatureStatistic.WRAITH),

    ESTATE(new Resources(2000, 5, 5, 0, 0, 0, 0), null, CreatureStatistic.VAMPIRE, CreatureStatistic.VAMPIRE_LORD),
    ESTATE_UPGRADED(new Resources(2000, 10, 0, 4, 0, 10, 10), ESTATE, CreatureStatistic.VAMPIRE, CreatureStatistic.VAMPIRE_LORD),

    MAUSOLEUM(new Resources(2000, 0, 4, 4, 4, 0, 0), null, CreatureStatistic.LICH, CreatureStatistic.POWER_LICH),
    MAUSOLEUM_UPGRADED(new Resources(2000, 0, 4, 4, 4, 0, 0), MAUSOLEUM, CreatureStatistic.LICH, CreatureStatistic.POWER_LICH),

    HALL_OF_DARKNESS(new Resources(6000, 10, 10, 4, 0, 0, 0), null, CreatureStatistic.BLACK_KNIGHT, CreatureStatistic.DREAD_KNIGHT),
    HALL_OF_DARKNESS_UPGRADED(new Resources(3000, 0, 5, 2, 2, 2, 2), HALL_OF_DARKNESS, CreatureStatistic.BLACK_KNIGHT, CreatureStatistic.DREAD_KNIGHT),

    DRAGON_VAULT(new Resources(10000, 5, 5, 5, 5, 5, 0), null, CreatureStatistic.BONE_DRAGON, CreatureStatistic.GHOST_DRAGON),
    DRAGON_VAULT_UPGRADED(new Resources(15000, 5, 5, 20, 0, 0, 0), DRAGON_VAULT, CreatureStatistic.BONE_DRAGON, CreatureStatistic.GHOST_DRAGON);

    private final Resources cost;
    private final UpgradeBuildings requiredBuilding;
    private final CreatureStatistic baseCreature;
    private final CreatureStatistic upgradedCreature;

    UpgradeBuildings(Resources cost, UpgradeBuildings requiredBuilding, CreatureStatistic baseCreature, CreatureStatistic upgradedCreature) {
        this.cost = cost;
        this.requiredBuilding = requiredBuilding;
        this.baseCreature = baseCreature;
        this.upgradedCreature = upgradedCreature;
    }

    public Resources getCost() {
        return cost;
    }

    public UpgradeBuildings getRequiredBuilding() {
        return requiredBuilding;
    }

    public CreatureStatistic getBaseCreature() {
        return baseCreature;
    }

    public CreatureStatistic getUpgradedCreature() {
        return upgradedCreature;
    }

    public boolean isUpgrade() {
        return requiredBuilding != null;
    }

    public static Optional<UpgradeBuildings> getBuildingForCreature(CreatureStatistic creature) {
        return Arrays.stream(values())
                .filter(b -> b.getBaseCreature() == creature || b.getUpgradedCreature() == creature)
                .filter(b -> {
                    // Ensure it matches the creature directly
                    if (b.getBaseCreature() == creature && !b.isUpgrade()) return true;
                    if (b.getUpgradedCreature() == creature && b.isUpgrade()) return true;
                    return false;
                })
                .findFirst();
    }


}
