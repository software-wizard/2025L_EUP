package pl.psi.map;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.*;
import pl.psi.map.buildings.bank.Bank;
import pl.psi.map.resources.Resources;
import pl.psi.map.resources.generators.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardEconomyEngine {

    public static final String HERO_MOVED = "HERO_MOVED";
    private final TurnQueueEconomy turnQueue;
    private final BoardEconomy board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    Map<Point, MapObjectIf> interactables = new HashMap<>();
    Map<Point, BuildingIf> buildings = new HashMap<>();
    private int turnCounter;


    public BoardEconomyEngine(final EconomyHero hero1, final EconomyHero hero2) {
        turnQueue = new TurnQueueEconomy(hero1, hero2);
        interactables.put(new Point(2,2),new GoldGenerator());
        interactables.put(new Point(3,2),new MercuryGenerator());
        interactables.put(new Point(4,2), new OreGenerator());
        interactables.put(new Point(5,2), new SulfurGenerator());
        interactables.put(new Point(6,2), new WoodGenerator());
        interactables.put(new Point(7,2), new CrystalGenerator());
        interactables.put(new Point(8,2), new GemGenerator());
        buildings.put(new Point(0,1), new Castle());
        buildings.put(new Point(10, 3), new Bank(new Resources(0,0,0,0,0,0,0)));
        board = BoardEconomy.builder()
                .addHero(hero1, 5)
                .addHero(hero2,14)
                .addInteractables(interactables)
                .addBuildings(buildings)
                .build();
    }

    public boolean canMove(final Point point) {
        return board.canMove(turnQueue.getCurrentHero(), point);
    }

    public boolean canAttack(final Point point) {
        return isHeroAdjacent(point) && getHero(point).isPresent();
    }

    public boolean canEnter(final Point point) {
        return isEnterable(point);
    }

    public boolean canInteract(Point point) {
        return getInteractable(point).isPresent();
    }

    public void move(final Point point) {
        board.move(turnQueue.getCurrentHero(), point);
        observerSupport.firePropertyChange(HERO_MOVED, null, point);
    }

    public void interact(final Point point){
        board.interact(turnQueue.getCurrentHero(), point);
    }

    public void enter(final Point point){
        EnterAction action = board.enter(turnQueue.getCurrentHero(), point);
        switch (action.getType()){
            case OPEN_SHOP -> openShop(action.getBuilding());
            case ENTER_BANK -> enterBank(action.getBuilding());
        }
    }

    public void secondInteraction(final Point point){
        EnterAction action = board.secondInteraction(turnQueue.getCurrentHero(), point);
        switch (action.getType()){
            case OPEN_UPGRADE -> openUpgrades(action.getBuilding());
        }
    }


    public Optional<EconomyHero> getHero(final Point point) {
        return board.getHero(point);
    }

    public EconomyHero getCurrentHero() {
        return turnQueue.getCurrentHero();
    }

    public Optional<MapObjectIf> getMapObject(final Point point) {
        return board.getObjectAt(point);
    }

    public Optional<InteractableIf> getInteractable(final Point point) {
        return board.getInteractableAt(point);
    }

    public Optional<BuildingIf> getBuilding(final Point point) {
        return board.getBuildingAt(point);
    }

    public void pass() {
        getCurrentHero().resetMoveRange();
        endOfTurn();
        turnQueue.next();
    }

    private void endOfTurn() { // called after each click of the pass button
        turnCounter++;
        if (turnCounter >= 2){
            turnCounter = 0;
            endOfDay();
        }
    }

    private void endOfDay(){ // called after both players pass
        generateResourcesEndDay();
    }

    private void generateResourcesEndDay(){
        for (MapObjectIf interactable : interactables.values()) {
            System.out.println(interactable); // jak zmienie to można wywalić instanceof
            if (interactable instanceof ResourceGenIf generator){
                    generator.generateResource();
                }
            }
        }


    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        turnQueue.addObserver(aObserver);
    }

    public boolean isCurrentHero(Point point) {
        return Optional.of(turnQueue.getCurrentHero()).equals(board.getHero(point));
    }

    public boolean isHero(Point point) {
        return board.getHero(point).isPresent();
    }

    public boolean isEnterable(final Point point) {
        return board.getBuildingAt(point).isPresent();
    }

    public boolean isHeroAdjacent(Point target) {
        Point heroPos = board.getPosition(getCurrentHero());
        return heroPos.distance(target) == 1; // Manhattan distance
    }


    public void openShop(BuildingIf buildingOpt) {
        observerSupport.firePropertyChange("OPEN_SHOP", null, new Object[]{getCurrentHero(), buildingOpt});
    }

    public void openUpgrades(BuildingIf buildingOpt) {
        observerSupport.firePropertyChange("OPEN_UPGRADES", null, new Object[]{getCurrentHero(),buildingOpt});
    }

    public void enterBank(BuildingIf building){
        observerSupport.firePropertyChange("ENTER_BANK", null, new Object[]{getCurrentHero(), building});
    }


}


