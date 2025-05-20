package pl.psi.map.buildings.enterAction;

import lombok.Getter;
import pl.psi.map.buildings.BuildingIf;

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
