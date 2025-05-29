package pl.psi.map;

import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.enterAction.EnterAction;

public interface MapObjectIf {
    String getPath();
    void endOfTurn(); // w tych bez wydarzeń na koniec zostaje pusty
    void enter(EconomyHero hero);
    void generateResource();
    void interact(EconomyHero hero);
    typeOfObject getTypeOfObject();
    EconomyHero getOwner();
    EnterAction onEnter();
    EnterAction secondInteraction();
    //TODO dopisać te metody, na koniec tury musi być wywoływane
    enum typeOfObject{
        GENERATOR,
        BUILDING,
        PICKUPABLE,
        UNKNOWN
    }
}
