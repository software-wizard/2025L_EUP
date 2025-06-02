package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.creatures.Creature;
import pl.psi.Exceptions.*;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Board {
    private static final int MAX_WITDH = 14;
    private final BiMap<Point, Creature> map = HashBiMap.create();
    private final BiMap<Point, SpecialField> mapWithSpecialFields = HashBiMap.create();

    public Board(final List<Creature> aCreatures1, final List<Creature> aCreatures2) {
        addCreatures(aCreatures1, 0);
        addCreatures(aCreatures2, MAX_WITDH);
    }

    public Board(final List<Creature> aCreatures1, final List<Creature> aCreatures2, BiMap<Point, SpecialField> aSpecialFields, final Map<Point, Creature> bankCreatures) {
        this(aCreatures1, aCreatures2);
        addSpecialFields(aSpecialFields);
        addCreaturesSetPositions(bankCreatures);
//        addCreaturesInCircle(aCreatures1, new Point(5,5),4.0);
    }

    private void addCreatures(final List<Creature> aCreatures, final int aXPosition) {
        for (int i = 0; i < aCreatures.size(); i++) {
            map.put(new Point(aXPosition, i * 2 + 1), aCreatures.get(i));
            aCreatures.get(i).setCurrentPoint(new Point(aXPosition, i * 2 + 1));
        }
    }

    private void addSpecialFields(final BiMap<Point, SpecialField> aSpecialFields) {
        for (BiMap.Entry<Point, SpecialField> entry : aSpecialFields.entrySet()) {
            mapWithSpecialFields.put(entry.getKey(), entry.getValue());
        }
    }

    private void addCreaturesSetPositions(final Map<Point, Creature> creaturesToPositions) {
        map.putAll(creaturesToPositions);
    }

    private void addCreaturesInCircle(final List<Creature> aCreatures, final Point center, final double radius) {
        int numberOfCreatures = aCreatures.size();

        for (int i = 0; i < numberOfCreatures; i++) {
            double angle = 2 * Math.PI * i / numberOfCreatures;
            int x = (int) Math.round(center.getX() + radius * Math.cos(angle));
            int y = (int) Math.round(center.getY() + radius * Math.sin(angle));

            Point position = new Point(x, y);
            map.put(position, aCreatures.get(i));
        }
    }

    Optional<Creature> getCreature(final Point aPoint) {
        return Optional.ofNullable(map.get(aPoint));
    }

    void move(final Creature aCreature, final Point aPoint) {
        if (canMove(aCreature, aPoint)) {
            try {
                if (mapWithSpecialFields.containsKey(aPoint)) {
                    SpecialField tile = mapWithSpecialFields.get(aPoint);
                    tile.doSomething(aCreature);
                }
                map.inverse()
                        .remove(aCreature);
                map.put(aPoint, aCreature);
                aCreature.setCurrentPoint(aPoint);
            } catch (CannotPassFieldException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Nie możesz przejść przez to pole",
                        "Błąd ruchu",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        }
    }

    boolean canMove(final Creature aCreature, final Point aPoint) {
        if (map.containsKey(aPoint)) {
            return false;
        }

//        if(mapWithSpecialFields.containsKey(aPoint)){
//            mapWithSpecialFields.get(aPoint).canInteract(aCreature);
//        }
        final Point oldPosition = getPosition(aCreature);
        return aPoint.distance(oldPosition.getX(), oldPosition.getY()) < aCreature.getMoveRange();
    }

    Point getPosition(Creature aCreature) {
        return map.inverse()
                .get(aCreature);
    }

    public BiMap<Point, SpecialField> getSpecialFields() {
        return mapWithSpecialFields;
    }

    void interact(Creature aCurrentCreature, Point aCurrentPoint) {
        if (mapWithSpecialFields.containsKey(aCurrentPoint)) {
            SpecialField tile = mapWithSpecialFields.get(aCurrentPoint);
            tile.doSomething(aCurrentCreature);
        }
    }
}
