package pl.psi.map.buildings.town;

import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.BuildingIf;
import pl.psi.map.buildings.enterAction.EnterAction;
import pl.psi.map.buildings.enterAction.EnterActionType;

import java.util.HashSet;
import java.util.Set;

public class Town implements BuildingIf {

    private final Set<TownBuilding> builtTownBuildings = new HashSet<>();
    private final Set<UpgradeBuildings> builtUpgradeBuildings = new HashSet<>();

    public void build(BuildingType building, EconomyHero hero) {
        building.buildIn(this, hero);
    }

    public void buildBuilding(BuildingType building, EconomyHero hero) {
        if (building.isBuiltIn(this)) {
            throw new IllegalStateException("Building already constructed.");
        }

        boolean allBuilt = building.getPrerequisites().stream()
                .allMatch(prereq -> prereq.isBuiltIn(this));

        if (!allBuilt) {
            throw new IllegalStateException("Prerequisites not met for " + building);
        }

        hero.pay(building.getCost());
        building.registerInTown(this);
    }


    public boolean hasBuilt(TownBuilding building) {
        return builtTownBuildings.contains(building);
    }

    public boolean hasBuilt(UpgradeBuildings building) {
        return builtUpgradeBuildings.contains(building);
    }

    public boolean hasBuilt(BuildingType building) {
        return building.isBuiltIn(this);
    }

    public void addTownBuilding(TownBuilding building) {
        builtTownBuildings.add(building);
    }

    public void addUpgradeBuilding(UpgradeBuildings building) {
        builtUpgradeBuildings.add(building);
    }

    @Override
    public String getPath() {
        return "/objects/castle.png";
    }

    @Override
    public void endOfTurn() {
    }

    @Override
    public void enter(EconomyHero hero) {

    }

    @Override
    public void generateResource() {
    }

    @Override
    public void interact(EconomyHero hero) {

    }

    @Override
    public typeOfObject getTypeOfObject() {
        return null;
    }

    @Override
    public EconomyHero getOwner() {
        return null;
    }

    @Override
    public EnterAction onEnter() {
        return new EnterAction(EnterActionType.OPEN_SHOP, this);
    }

    @Override
    public EnterAction secondInteraction() {
        return new EnterAction(EnterActionType.OPEN_UPGRADE, this);
    }


}
