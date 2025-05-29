package pl.psi.map;

import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.enterAction.EnterAction;

public class testMapObject implements MapObjectIf {
    @Override
    public String getPath() {
        return "";
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
        return typeOfObject.BUILDING;
    }

    @Override
    public EconomyHero getOwner() {
        return null;
    }

    @Override
    public EnterAction onEnter() {
        return null;
    }

    @Override
    public EnterAction secondInteraction() {
        return null;
    }
}
