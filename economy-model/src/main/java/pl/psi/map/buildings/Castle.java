package pl.psi.map.buildings;

import pl.psi.creatures.UpgradeBuildings;
import pl.psi.hero.EconomyHero;
import pl.psi.map.MapObjectIf;

import java.util.HashSet;
import java.util.Set;

public class Castle implements BuildingIf {

    private final Set<UpgradeBuildings> builtBuildings = new HashSet<>();

    public void build(UpgradeBuildings building, EconomyHero hero) {
        // Check if already built
        if (hasBuilt(building)) {
            throw new IllegalStateException("Building already constructed.");
        }

        // Check prerequisites
        if (building.isUpgrade() && !hasBuilt(building.getRequiredBuilding())) {
            throw new IllegalStateException("You must build the base version first.");
        }

        // Check resource availability
        if (!hero.canAfford(building.getCost())) {
            throw new IllegalStateException("Not enough resources to build " + building.name());
        }

        // Pay and build
        hero.pay(building.getCost());
        builtBuildings.add(building);
    }

    public boolean hasBuilt(UpgradeBuildings building) {
        return builtBuildings.contains(building);
    }

    public Set<UpgradeBuildings> getBuiltBuildings() {
        return Set.copyOf(builtBuildings);
    }

    @Override
    public String getPath() {
        return "/objects/castle.png";
    }

    @Override
    public void Enter() {
    }

    @Override
    public void endOfTurn() {
    }

    @Override
    public void enter() {
    }

    @Override
    public void generateResource() {
    }

    @Override
    public EconomyHero getOwner() {
        return null;
    }
}
