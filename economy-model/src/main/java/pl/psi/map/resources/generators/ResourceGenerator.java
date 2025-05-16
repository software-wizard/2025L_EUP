package pl.psi.map.resources.generators;

import lombok.Getter;
import pl.psi.hero.EconomyHero;
import pl.psi.map.InteractableIf;
import pl.psi.map.buildings.enterAction.EnterAction;

public class ResourceGenerator implements InteractableIf, ResourceGenIf {

    private final ResourceGenType type;
    @Getter
    private EconomyHero owner;

    public ResourceGenerator(ResourceGenType type) {
        this.type = type;
    }

    public void interact(EconomyHero hero) {
        if (owner != hero) {
            owner = hero;
            System.out.println("New owner: " + owner);
        }
    }

    public void generateResource() {
        if (owner != null) {
            owner.addResource(type.getResources());
        }
    }

    @Override
    public void endOfTurn() {}

    @Override
    public void enter(EconomyHero hero) {}

    @Override
    public EnterAction onEnter() {
        return null;
    }

    @Override
    public EnterAction secondInteraction() {
        return null;
    }

    @Override
    public String getPath() {
        return type.getImagePath();
    }

    @Override
    public typeOfObject getTypeOfObject() {
        return null;
    }
}
