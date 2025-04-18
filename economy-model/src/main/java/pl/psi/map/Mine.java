package pl.psi.map;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;


public class Mine implements InteractableIf, ResourceGenIf{
    private final int yieldAmount;
    private EconomyHero owner;


    public Mine(int yieldAmount){
        this.yieldAmount = yieldAmount;
    }


    @Override
    public void interact(EconomyHero hero, BoardEconomy board, Point point) {


         //po wejściu na pole owner zmienia się na hero który wszedł i co kliknięcie pass dodaje golda
        // pokminić czy zrobić to w hero (lista kopalni) czy tak jak tutaj 

        }

    @Override
    public void generateResource(EconomyHero owner) {
        owner.addGold(yieldAmount);
    }
}

