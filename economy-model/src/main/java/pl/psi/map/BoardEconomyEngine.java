package pl.psi.map;

import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.enterAction.EnterAction;
import pl.psi.map.buildings.BuildingIf;
import pl.psi.economy.Point;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.Optional;

public class BoardEconomyEngine {

    public static final String HERO_MOVED = "HERO_MOVED";
    private final TurnQueueEconomy turnQueue;
    private final BoardEconomy board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    Map<Point, MapObjectIf> interactables;
    private int turnCounter;


    public BoardEconomyEngine(final EconomyHero hero1, final EconomyHero hero2, Map<Point, MapObjectIf> map) {
        this.interactables = map;
        turnQueue = new TurnQueueEconomy(hero1, hero2);
        board = BoardEconomy.builder()
                .addHero(hero1, new Point(0,0))
                .addHero(hero2,new Point(17,8))
                .addInteractables(map)
                .build();
    }

    public boolean canMove(final Point point) {
        if (!isHero(point)) {
            return board.canMove(turnQueue.getCurrentHero(), point);
        }
        return false;
    }

    public boolean canAttack(final Point point) {
        return isHeroAdjacent(point) && getHero(point).isPresent();
    }

    public boolean canEnter(final Point point) {
        if (!isHero(point)) {
            return isEnterable(point);
        }
        return false;
    }

    public boolean canInteract(Point point) {
        if (!isHero(point)) {
            return getInteractable(point).isPresent();
        }
        return false;
    }

    public void move(final Point point) {
        board.move(turnQueue.getCurrentHero(), point);
        observerSupport.firePropertyChange(HERO_MOVED, null, point);
    }

    public void interact(final Point point) {
        board.interact(turnQueue.getCurrentHero(), point);
    }

    public void enter(final Point point) {
        EnterAction action = board.enter(point);
        switch (action.getType()) {
            case OPEN_SHOP: {
                openShop(action.getBuilding());
                break;
            }
            case ENTER_BANK: {
                enterBank(action.getBuilding());
                break;
            }
        }
    }

    public void secondInteraction(final Point point) {
        EnterAction action = board.secondInteraction(point);
        switch (action.getType()) {
            case OPEN_UPGRADE:{
                openUpgrades(action.getBuilding());
                break;
            }
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

    public void pass() {
        getCurrentHero().resetMoveRange();
        endOfTurn();
        turnQueue.next();
    }

    private void endOfTurn() { // called after each click of the pass button
        turnCounter++;
        System.out.println("End of turn");
        if (turnCounter >= 2){
            turnCounter = 0;
            endOfDay();
        }
    }

    private void endOfDay(){ // called after both players pass
        System.out.println("End of day");
        generateResourcesEndDay();
    }

    private void generateResourcesEndDay(){
        for (MapObjectIf interactable : interactables.values()) {
            interactable.generateResource();
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


