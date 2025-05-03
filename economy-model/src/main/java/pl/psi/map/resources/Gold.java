package pl.psi.map.resources;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.BoardEconomy;
import pl.psi.map.InteractableIf;

public class Gold implements InteractableIf {
    private final Resources resources;

    public Gold(Resources goldToGain){
        this.resources = goldToGain;
    }

    @Override
    public void interact(EconomyHero hero, BoardEconomy board, Point point) {
        hero.addResource(resources);
        board.removeInteractableAt(point);
        System.out.println("Gold interacted");
    }

    public String getPath(){
        return "/objects/goldPile1.png";
    }

    @Override
    public void endOfTurn() {

    }

    @Override
    public void enter() {

    }

    @Override
    public void generateResource() {

    }

    @Override
    public EconomyHero getOwner() {
        return null;
    }
}
