package pl.psi.hero;

import lombok.Getter;
import pl.psi.Point;
import pl.psi.map.InteractableIf;
import pl.psi.map.MapObjectIf;
import pl.psi.map.buildings.enterAction.EnterAction;

public class Artifact implements InteractableIf {
    private String name;
    @Getter
    private Statistics bonuses;

    public Artifact(String name, int attackBonus, int defenseBonus, int powerBonus, int knowledgeBonus) {
        this.name = name;
        this.bonuses = new Statistics(attackBonus, defenseBonus, powerBonus, knowledgeBonus);
    }

    @Override
    public String getPath() {
        return "/objects/Artifact_Sword_of_Hellfire.gif";
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
        hero.addArtifact(this);
        System.out.println("Artifact " + name + " interacted");
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
