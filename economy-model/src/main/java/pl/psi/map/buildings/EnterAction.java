package pl.psi.map.buildings;

import lombok.Getter;

public class EnterAction {
    @Getter
    private final EnterActionType type;
    @Getter
    private final BuildingIf building;

    public EnterAction(EnterActionType type, BuildingIf building){
        this.type = type;
        this.building = building;
    }
}
