package pl.psi;

import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class TurnQueue {
    public static final String TURN_END = "TURN_END";
    private final LinkedList<Creature> queue;
    private final Collection<Creature> allCreatures;
    private Creature activeCreature;
    private final PropertyChangeSupport propertyChangeSupport;

    public TurnQueue(List<Creature> aC1, List<Creature> aC2) {
        queue = new LinkedList<>();
        allCreatures = Stream.concat(aC1.stream(), aC2.stream()).sorted(Comparator.comparingInt(Creature::getMoveRange).reversed())
                .toList();

        queue.addAll(allCreatures);
        pass();

        propertyChangeSupport = new PropertyChangeSupport(this);
        allCreatures.forEach(c -> propertyChangeSupport.addPropertyChangeListener(c));
    }

    Creature getActiveCreature() {
        return activeCreature;
    }

    void pass() {
        if (queue.isEmpty()) {
            queue.addAll(allCreatures);
            propertyChangeSupport.firePropertyChange(TURN_END, null, null);
        }

        activeCreature = queue.poll();
    }
}
