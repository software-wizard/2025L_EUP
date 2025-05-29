package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.creatures.Creature;

import java.util.ArrayList;
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
        }
    }

    private void addSpecialFields(final BiMap<Point, SpecialField> aSpecialFields) {
        for (BiMap.Entry<Point, SpecialField> entry : aSpecialFields.entrySet()) {
            mapWithSpecialFields.put(entry.getKey(), entry.getValue());
        }
    }

    //Utworzyłem te metodę, aby móc dodawać nowe pola specjalne do istniejącej planszy np. za pomocą zaklęć
    public void addSpecialFieldOpen(final BiMap<Point, SpecialField> aSpecialFields){
        for (Point point : aSpecialFields.keySet()) {
            mapWithSpecialFields.put(point, aSpecialFields.get(point));
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
            List<Point> path = examinePath(getPosition(aCreature), aPoint);

            for (int i = 0; i < path.size()-1; i++) {
                if (mapWithSpecialFields.containsKey(path.get(i))) {
                    SpecialField currentField = mapWithSpecialFields.get(path.get(i));

                    //Ten warunek sprawdza, czy pole specjalne na ściezce ruchu powinno aktywowac sie po przejsciu jednostki
                    if (currentField.getTypeOfField().equals(FieldType.TRIGGERED_BY_STEPPING)) {
                        currentField.doSomething(aCreature);
                    }
                }
            }

            //jesli jednostka nie umarła podczas ruchu to sprawdzane jest ostatnie pole
            if (!aCreature.isAlive()) {
                return;
            }
        }



        if (canMove(aCreature, aPoint)) {
            if (mapWithSpecialFields.containsKey(aPoint)) {
                SpecialField tile = mapWithSpecialFields.get(aPoint);
                tile.doSomething(aCreature);
            }
            map.inverse()
                    .remove(aCreature);
            map.put(aPoint, aCreature);
        }
    }

    boolean canMove(final Creature aCreature, final Point aPoint) {
        if (map.containsKey(aPoint)) {
            return false;
        }
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

    public void removeCreature(Creature creature) {
        map.inverse().remove(creature);
    }

    //Metoda ma na celu określenie trasy po której nastąpił ruch,
    public List<Point> examinePath(Point start, Point end) {

        List<Point> path = new ArrayList<>();

        //zmienne określające kierunek w zależności od pozycji
        int dx = Integer.signum(end.getX() - start.getX());
        int dy = Integer.signum(end.getY() - start.getY());


        //współrzędne startowe
        int x = start.getX();
        int y = start.getY();


        while (x != end.getX() || y != end.getY()) {
            if (x != end.getX()) x += dx;
            if (y != end.getY()) y += dy;
            path.add(new Point(x, y));
        }

        return path;
    }
}
