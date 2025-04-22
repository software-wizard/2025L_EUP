package pl.psi.map;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.resources.Gold;
import pl.psi.map.resources.GoldGenerator;
import pl.psi.map.resources.ResourceGenIf;
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


    public BoardEconomyEngine(final EconomyHero hero1, final EconomyHero hero2) {
        turnQueue = new TurnQueueEconomy(hero1, hero2);
        interactables.put(new Point(2,2),new GoldGenerator(new Resources(500,0,0,0,0,0,0)));
        interactables.put(new Point(5,5),new Gold(new Resources(100,0,0,0,0,0,0)));
        board = BoardEconomy.builder()
                .addHero(hero1, 0)
                .addHero(hero2,14)
                .addInteractables(interactables)
                .build();
    }

    public boolean canMove(final Point point) {
        return board.canMove(turnQueue.getCurrentHero(), point);
    }

    public boolean canAttack(final Point point) {
        return nextToHelper(point);
    }

    public boolean canCapture(final Point point) {
        return nextToHelper(point);
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

    public void pass() {
        endOfTurnEvent();
        turnQueue.next();
    }
    public void endOfTurnEvent() {
        System.out.println("End of turn event");
        for (InteractableIf interactable : interactables.values()) {
            System.out.println(interactable);
            if (interactable instanceof ResourceGenIf generator) {
                System.out.println(generator);
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

    private boolean nextToHelper(final Point point) {
        double distance = board.getPosition(turnQueue.getCurrentHero())
                .distance(point);
        return board.getHero(point)
                .isPresent()
                && distance < 2 && distance > 0;
    }

    public void openShop(EconomyHero hero1){

    }
    }


