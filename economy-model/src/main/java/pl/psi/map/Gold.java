package pl.psi.map;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;

public class Gold implements InteractableIf{
    private final int amount;

    public Gold(int amount){
        this.amount = amount;
    }

    @Override
    public void interact(EconomyHero hero, BoardEconomy board, Point point) {
        hero.addGold(amount);
        board.removeInteractableAt(point);
    }
}
