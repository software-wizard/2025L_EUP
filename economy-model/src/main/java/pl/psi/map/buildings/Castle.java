package pl.psi.map.buildings;

import pl.psi.map.MapObjectIf;

public class Castle implements BuildingIf{

   @Override
    public String getPath() {
        return "/objects/castle.png";
    }

    @Override
    public void Enter() {
    }
}
