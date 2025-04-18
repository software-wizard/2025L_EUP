package pl.psi.map.resources;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.BoardEconomy;
import pl.psi.map.InteractableIf;


public class GoldGenerator implements InteractableIf, ResourceGenIf {
    private final Resources resources;
    private EconomyHero owner;


    public GoldGenerator(Resources resources1) {
        this.resources = resources1;
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
        return "/objects/goldGenerator.png";
    }

    public void generateResource(){
        System.out.println("Generating resource for: " + owner);
        owner.addResource(resources);
        System.out.println(owner.getResources().getGold());
    }
}

