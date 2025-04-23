package pl.psi.map.buildings;

import pl.psi.map.MapObjectIf;

public interface BuildingIf extends MapObjectIf {
    typeOfObject type = typeOfObject.BUILDING;
    public void Enter();
}
