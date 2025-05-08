package pl.psi.map.buildings;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.BoardEconomy;
import pl.psi.map.resources.Resources;

public class Bank implements BuildingIf{
    final Resources prize;

    public Bank(Resources prize) {
        this.prize = prize;
    }

    @Override
    public String getPath() {
        return "";
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
    public void interact(EconomyHero hero, BoardEconomy board, Point point) {

    }

    @Override
    public EconomyHero getOwner() {
        return null;
    }

    @Override
    public EnterAction onEnter() {
        return new EnterAction(EnterActionType.ENTER_BANK, this);
    }

    @Override
    public EnterAction secondInteraction() {
        return null;
    }
}
