package pl.psi.map;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;

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
        interactables.put(new Point(5,5),new Gold(500));
        board = BoardEconomy.builder()
                .addHero(hero1, 0)
                .addHero(hero2,14)
                .addInteractables(interactables)
                .build();
    }

    public boolean canMove(final Point point) {
        return board.canMove(turnQueue.getCurrentHero(), point);
    }

    public void move(final Point point) {
        board.move(turnQueue.getCurrentHero(), point);
        observerSupport.firePropertyChange(HERO_MOVED, null, point);
    }

    public Optional<EconomyHero> getHero(final Point point) {
        return board.getHero(point);
    }

    public void pass() {
        turnQueue.next();
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        turnQueue.addObserver(aObserver);
    }
//
//    public boolean canAttack(final Point point) {
//        double distance = board.getPosition(turnQueue.getCurrentCreature())
//                .distance(point);
//        return board.getCreature(point)
//                .isPresent()
//                && distance < 2 && distance > 0;
//    }

    public boolean isCurrentHero(Point point) {
        return Optional.of(turnQueue.getCurrentHero()).equals(board.getHero(point));
    }

    public boolean isInteractable(Point point) {
        Object obj = board.getInteractableAt(point);
        return obj != null;
    }

}
