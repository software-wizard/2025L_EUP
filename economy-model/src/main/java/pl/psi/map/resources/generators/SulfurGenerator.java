package pl.psi.map.resources.generators;

import lombok.Getter;
import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.BoardEconomy;
import pl.psi.map.InteractableIf;
import pl.psi.map.buildings.EnterAction;
import pl.psi.map.resources.Resources;


public class SulfurGenerator implements InteractableIf, ResourceGenIf {
    private final Resources resources;
    @Getter
    private EconomyHero owner;


    public SulfurGenerator() {
        this.resources = new Resources(0,0,0,0,1,0,0);
    }


    public void interact(EconomyHero hero, BoardEconomy board, Point point) {

        if(owner != hero){
            owner = hero;
            System.out.println("New owner: " + owner);
        }


        //po wejściu na pole owner zmienia się na hero który wszedł i co kliknięcie pass dodaje golda
        // pokminić czy zrobić to w hero (lista kopalni) czy tak jak tutaj

    }

    @Override
    public String getPath() {
        return "/objects/sulfur_mound.jpg";
    }

    public void generateResource(){
        if(owner != null) {
            owner.addResource(resources);
        }
    }

    @Override
    public void endOfTurn() {

    }

    @Override
    public void enter(EconomyHero hero) {

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

