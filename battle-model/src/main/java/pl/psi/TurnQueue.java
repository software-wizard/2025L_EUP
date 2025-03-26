package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.psi.creatures.Creature;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class TurnQueue {

    public static final String END_OF_TURN = "END_OF_TURN";
    public static final String NEXT_CREATURE = "NEXT_CREATURE";
    private final Collection<Creature> creatures;
    private final Queue<Creature> creaturesQueue;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private Creature currentCreature;
    private int roundNumber;

    public TurnQueue(final Collection<Creature> aCreatureList,
                     final Collection<Creature> aCreatureList2) {
        creatures = Stream.concat(aCreatureList.stream(), aCreatureList2.stream())
                .collect(Collectors.toList());
        creaturesQueue = new LinkedList<>();
        initQueue();
        creatures.forEach(observerSupport::addPropertyChangeListener);
        next();
    }

    private void initQueue() {
        creaturesQueue.addAll(creatures);
    }

    public Creature getCurrentCreature() {
        return currentCreature;
    }

    public void next() {
        Creature oldCreature = currentCreature;
        if (creaturesQueue.isEmpty()) {
            endOfTurn();
        }
        currentCreature = creaturesQueue.poll();
        observerSupport.firePropertyChange(NEXT_CREATURE, oldCreature, currentCreature);
    }

    private void endOfTurn() {
        roundNumber++;
        initQueue();
        observerSupport.firePropertyChange(END_OF_TURN, roundNumber - 1, roundNumber);
    }

    void addObserver(PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
    }
}
