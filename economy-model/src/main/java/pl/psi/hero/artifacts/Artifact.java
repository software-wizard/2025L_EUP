package pl.psi.hero.artifacts;

import lombok.Getter;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.Statistics;
import pl.psi.map.InteractableIf;
import pl.psi.map.buildings.enterAction.EnterAction;
import pl.psi.map.resources.Resources;

public class Artifact implements InteractableIf {
    @Getter
    private final ArtifactType type;

    public Artifact(ArtifactType type) {
        this.type = type;
    }

    public Statistics getStats(){
        return type.getStatistics();
    }

    @Override
    public String toString(){
        return type.getName();
    }

    @Override
    public String getPath() {
        return type.getImagePath();
    }

    public int getCost() {
        return type.getCost();
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
        hero.addArtifact(this);
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
