package pl.psi.map.buildings;

import pl.psi.hero.EconomyHero;
import pl.psi.map.MapObjectIf;
import pl.psi.map.buildings.enterAction.EnterAction;

public class Chapel implements MapObjectIf {
    @Override
    public String getPath() {
        return "/objects/słonce.jpg";
    }

    @Override
    public void endOfTurn() {

    }

    @Override
    public void enter(EconomyHero hero) {
        //hero.addSpell();
    }

    @Override
    public void generateResource() {

    }

    @Override
    public void interact(EconomyHero hero) {

    }

    @Override
    public typeOfObject getTypeOfObject() {
        return typeOfObject.PICKUPABLE;
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
