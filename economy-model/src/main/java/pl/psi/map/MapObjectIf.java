package pl.psi.map;

public interface MapObjectIf {
    String getPath();
//    void endOfTurn(); // w tych bez wydarzeń na koniec zostaje pusty
    //TODO dopisać te metody, na koniec tury musi być wywoływane
    public enum typeOfObject{
        GENERATOR,
        BUILDING,
        PICKUPABLE,
    }
}
