package pl.psi.map.buildings.bank;

import lombok.Getter;
import pl.psi.economy.Point;
import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.BuildingIf;
import pl.psi.map.buildings.enterAction.EnterAction;
import pl.psi.map.buildings.enterAction.EnterActionType;
import pl.psi.map.resources.Resources;

import java.util.Map;

public class Bank implements BuildingIf {
    final Resources prize;
    @Getter
    final Map<Point, EconomyCreature> enemies;

    public Bank(BankStatistics statistics) {
        this.prize = statistics.getPrize();
        this.enemies = statistics.getEnemies();
    }

    @Override
    public String getPath() {
        return "/objects/bank.png";
    }

    @Override
    public void endOfTurn() {}

    @Override
    public void enter(EconomyHero hero) {

    }

    @Override
    public void generateResource() {

    }

    @Override
    public void interact(EconomyHero hero) {

    }

    @Override
    public typeOfObject getTypeOfObject() {
        return null;
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
