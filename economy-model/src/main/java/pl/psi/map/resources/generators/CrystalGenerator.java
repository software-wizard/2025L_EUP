package pl.psi.map.resources.generators;

import lombok.Getter;
import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.BoardEconomy;
import pl.psi.map.InteractableIf;
import pl.psi.map.resources.Resources;


public class CrystalGenerator implements InteractableIf, ResourceGenIf {
    private final Resources resources;
    @Getter
    private EconomyHero owner;


    public CrystalGenerator() {
        this.resources = new Resources(0,0,0,0,0,1,0);
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
        return "/objects/crystal_mine.jpg";
    }

    public void generateResource(){
        System.out.println("Generating resource for: " + owner);
        owner.addResource(resources);
        System.out.println(owner.getResources().getGold());
    }
}

