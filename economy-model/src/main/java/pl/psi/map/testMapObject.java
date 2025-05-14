package pl.psi.map;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.enterAction.EnterAction;

import java.util.Map;

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
    public void interact(EconomyHero hero, Point point) {

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
