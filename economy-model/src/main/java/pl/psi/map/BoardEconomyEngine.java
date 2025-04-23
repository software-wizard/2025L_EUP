package pl.psi.map;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.BuildingIf;
import pl.psi.map.buildings.Castle;
import pl.psi.map.resources.Gold;
import pl.psi.map.resources.generators.GoldGenerator;
import pl.psi.map.resources.generators.MercuryGenerator;
import pl.psi.map.resources.generators.OreGenerator;
import pl.psi.map.resources.generators.ResourceGenIf;
import pl.psi.map.resources.Resources;

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
    Map<Point, InteractableIf> interactables = new HashMap<>();
    Map<Point, BuildingIf> buildings = new HashMap<>();


    public BoardEconomyEngine(final EconomyHero hero1, final EconomyHero hero2) {
        turnQueue = new TurnQueueEconomy(hero1, hero2);
        interactables.put(new Point(2,2),new GoldGenerator());
        interactables.put(new Point(5,5),new Gold(new Resources()));
        interactables.put(new Point(1,10),new MercuryGenerator());
        interactables.put(new Point(10,10), new OreGenerator());
        buildings.put(new Point(8,8), new Castle());
        board = BoardEconomy.builder()
                .addHero(hero1, 0)
                .addHero(hero2,14)
                .addInteractables(interactables)
                .addBuildings(buildings)
                .build();
    }

    public boolean canMove(final Point point) {
        return board.canMove(turnQueue.getCurrentHero(), point);
    }

    public boolean canAttack(final Point point) {
        return nextToHelper(point) && getHero(point).isPresent();
    }

    public boolean canEnterCastle(final Point point) {
        return nextToHelper(point) && isEnterable(point);
    }

    public boolean canInteract(Point point) {
        return getInteractable(point).isPresent();
    }

    public void move(final Point point) {
        board.move(turnQueue.getCurrentHero(), point);
        observerSupport.firePropertyChange(HERO_MOVED, null, point);
    }

    public Optional<EconomyHero> getHero(final Point point) {
        return board.getHero(point);
    }

    public EconomyHero getCurrentHero() {
        return turnQueue.getCurrentHero();
    }

    public Optional<InteractableIf> getInteractable(final Point point) {
        return board.getInteractableAt(point);
    }

    public Optional<BuildingIf> getBuilding(final Point point) {
        return board.getBuildingAt(point);
    }

    public void pass() {
        getCurrentHero().resetMoveRange();
        endOfTurnEvent();
        turnQueue.next();
        System.out.println("Current hero after pass: " + getCurrentHero().getResources());
    }

    public void endOfTurnEvent() {
        System.out.println("End of turn event");
        for (InteractableIf interactable : interactables.values()) {
            System.out.println(interactable);
            if (interactable instanceof ResourceGenIf generator){
                if(generator.getOwner() == turnQueue.getCurrentHero()){
                    System.out.println(generator);
                    generator.generateResource();
                }
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

    public boolean isEnterable(final Point point) {
        return Optional.of(board.getBuildingAt(point).isPresent()).orElse(false);
    }

    private boolean nextToHelper(final Point point) {
        double distance = board.getPosition(turnQueue.getCurrentHero())
                .distance(point);
        return board.getHero(point)
                .isPresent()
                && distance < 2 && distance > 0;
    }

    }


