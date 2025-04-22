package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import pl.psi.creatures.Creature;
import pl.psi.creatures.MoraleCreature;
import pl.psi.creatures.LuckyCreature;


/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngine {

    public static final String CREATURE_MOVED = "CREATURE_MOVED";
    private final TurnQueue turnQueue;
    private final Board board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);

    public GameEngine(final Hero aHero1, final Hero aHero2) {
        List<Creature> hero1Creatures = aHero1.getCreatures().stream()
                .map(c -> decorateCreature(c, aHero1))
                .collect(Collectors.toList());

        List<Creature> hero2Creatures = aHero2.getCreatures().stream()
                .map(c -> decorateCreature(c, aHero2))
                .collect(Collectors.toList());


        turnQueue = new TurnQueue(hero1Creatures, hero2Creatures);
        board = new Board(hero1Creatures, hero2Creatures);
    }
    private Creature decorateCreature(Creature base, Hero hero) {
        return new LuckyCreature(new MoraleCreature(base, hero), hero);
    }


    public void attack(final Point point) {
        Creature currentCreature = turnQueue.getCurrentCreature();

        if (currentCreature instanceof MoraleCreature) {
            MoraleCreature morale = (MoraleCreature) currentCreature;
            if (morale.shouldSkipTurn()) {
                pass();
                return;
            }
        }

        board.getCreature(point)
                .ifPresent(defender -> currentCreature.attack(defender));

        if (currentCreature instanceof MoraleCreature) {
            MoraleCreature morale = (MoraleCreature) currentCreature;
            if (morale.shouldGetExtraTurn()) {
                return;
            }
        }
        pass();
    }


    public boolean canMove(final Point aPoint) {
        return board.canMove(turnQueue.getCurrentCreature(), aPoint);
    }

    public void move(final Point aPoint) {
        Creature currentCreature = turnQueue.getCurrentCreature();
        if (currentCreature instanceof MoraleCreature) {
            MoraleCreature morale = (MoraleCreature) currentCreature;
            if (morale.shouldSkipTurn()) {
                pass();
                return;
            }
        }


        board.move(currentCreature, aPoint);
        observerSupport.firePropertyChange(CREATURE_MOVED, null, aPoint);

        if (currentCreature instanceof MoraleCreature) {
            MoraleCreature morale = (MoraleCreature) currentCreature;
            if (morale.shouldGetExtraTurn()) {
                return;
            }
        }

        pass();

    }

    public Optional<Creature> getCreature(final Point aPoint) {
        return board.getCreature(aPoint);
    }

    public void pass() {
        turnQueue.next();
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        turnQueue.addObserver(aObserver);
    }

    public boolean canAttack(final Point point) {
        double distance = board.getPosition(turnQueue.getCurrentCreature())
                .distance(point);
        return board.getCreature(point)
                .isPresent()
                && distance < 2 && distance > 0;
    }

    public boolean isCurrentCreature(Point aPoint) {
        return Optional.of(turnQueue.getCurrentCreature()).equals(board.getCreature(aPoint));
    }
}
