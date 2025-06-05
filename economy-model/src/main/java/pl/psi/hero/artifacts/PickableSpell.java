package pl.psi.hero.artifacts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.psi.hero.EconomyHero;
import pl.psi.map.InteractableIf;
import pl.psi.map.buildings.enterAction.EnterAction;

@AllArgsConstructor
public class PickableSpell implements InteractableIf {

    @Getter
    private final String name;

    @Override
    public String getPath() {
        return "/objects/Artifact_Titan's_Cuirass.gif";
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
        hero.addSpell(this);
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
