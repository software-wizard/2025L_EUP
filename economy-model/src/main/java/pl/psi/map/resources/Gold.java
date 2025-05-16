package pl.psi.map.resources;

import pl.psi.hero.EconomyHero;
import pl.psi.map.InteractableIf;
import pl.psi.map.buildings.enterAction.EnterAction;

public class Gold implements InteractableIf {
    private final Resources resources;

    public Gold(Resources goldToGain){
        this.resources = goldToGain;
    }

    @Override
    public void interact(EconomyHero hero) {
        hero.addResource(resources);
        System.out.println("Gold interacted");
    }

    @Override
    public typeOfObject getTypeOfObject() {
        return typeOfObject.PICKUPABLE;
    }

    public String getPath(){
        return "/objects/goldPile1.png";
    }

    @Override
    public void endOfTurn() {}

    @Override
    public void enter(EconomyHero hero) {}

    @Override
    public void generateResource() {}

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
