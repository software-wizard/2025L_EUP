package pl.psi.map;

public interface MapObjectIf {
    String getPath();
    public enum typeOfObject{
        GENERATOR,
        BUILDING,
        PICKUPABLE,
    }
}
