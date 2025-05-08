package pl.psi.map;

import lombok.Getter;
import pl.psi.hero.EconomyHero;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TurnQueueEconomy {

    public static final String END_OF_TURN = "END_OF_TURN";
    public static final String NEXT_HERO = "NEXT_HERO";
    private final Collection<EconomyHero> heroes;
    private final Queue<EconomyHero> heroQueue;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    @Getter
    private EconomyHero currentHero;
    private int roundNumber;

    public TurnQueueEconomy(final EconomyHero hero1, final EconomyHero hero2) {

        heroes = Arrays.asList(hero1, hero2);
        heroQueue = new LinkedList<>();
        initQueue();
        heroes.forEach(observerSupport::addPropertyChangeListener);
        next();
    }

    private void initQueue() {
        heroQueue.addAll(heroes);
    }

    public void next() {
        EconomyHero oldHero = currentHero;
        if (heroQueue.isEmpty()) {
            endOfTurn();
        }
        currentHero = heroQueue.poll();
        observerSupport.firePropertyChange(NEXT_HERO, oldHero, currentHero);
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
