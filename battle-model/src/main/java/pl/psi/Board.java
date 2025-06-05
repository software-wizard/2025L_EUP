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
    private final BiMap<BattlePoint, Creature> map = HashBiMap.create();
    private final BiMap<BattlePoint, SpecialField> mapWithSpecialFields = HashBiMap.create();

    public Board(final List<Creature> aCreatures1, final List<Creature> aCreatures2) {
        addCreatures(aCreatures1, 0);
        addCreatures(aCreatures2, MAX_WITDH);
    }

    public Board(final List<Creature> aCreatures1, final List<Creature> aCreatures2, BiMap<BattlePoint, SpecialField> aSpecialFields, final Map<BattlePoint, Creature> bankCreatures) {
        this(aCreatures1, aCreatures2);
        addSpecialFields(aSpecialFields);
        addCreaturesSetPositions(bankCreatures);
//        addCreaturesInCircle(aCreatures1, new Point(5,5),4.0);
    }

    private void addCreatures(final List<Creature> aCreatures, final int aXPosition) {
        for (int i = 0; i < aCreatures.size(); i++) {
            map.put(new BattlePoint(aXPosition, i * 2 + 1), aCreatures.get(i));
        }
    }

    private void addSpecialFields(final BiMap<BattlePoint, SpecialField> aSpecialFields) {
        for (BiMap.Entry<BattlePoint, SpecialField> entry : aSpecialFields.entrySet()) {
            mapWithSpecialFields.put(entry.getKey(), entry.getValue());
        }
    }

    //Utworzyłem te metodę, aby móc dodawać nowe pola specjalne do istniejącej planszy np. za pomocą zaklęć
    public void addSpecialFieldOpen(final BiMap<BattlePoint, SpecialField> aSpecialFields) {
        for (BattlePoint battlePoint : aSpecialFields.keySet()) {
            mapWithSpecialFields.put(battlePoint, aSpecialFields.get(battlePoint));
        }
    }


    private void addCreaturesSetPositions(final Map<BattlePoint, Creature> creaturesToPositions) {
        map.putAll(creaturesToPositions);
    }

    private void addCreaturesInCircle(final List<Creature> aCreatures, final BattlePoint center, final double radius) {
        int numberOfCreatures = aCreatures.size();

        for (int i = 0; i < numberOfCreatures; i++) {
            double angle = 2 * Math.PI * i / numberOfCreatures;
            int x = (int) Math.round(center.getX() + radius * Math.cos(angle));
            int y = (int) Math.round(center.getY() + radius * Math.sin(angle));

            BattlePoint position = new BattlePoint(x, y);
            map.put(position, aCreatures.get(i));
        }
    }

    Optional<Creature> getCreature(final BattlePoint aBattlePoint) {
        return Optional.ofNullable(map.get(aBattlePoint));
    }

    void move(final Creature aCreature, final BattlePoint aBattlePoint) {

        if (canMove(aCreature, aBattlePoint)) {
            List<BattlePoint> path = examinePath(getPosition(aCreature), aBattlePoint);

            for (int i = 0; i < path.size() - 1; i++) {
                if (mapWithSpecialFields.containsKey(path.get(i))) {
                    SpecialField currentField = mapWithSpecialFields.get(path.get(i));

                    //Ten warunek sprawdza, czy pole specjalne na ściezce ruchu powinno aktywowac sie po przejsciu jednostki
                    if (currentField.getFieldName().equals(FieldType.TRIGGERED_BY_STEPPING)) {
                        currentField.doSomething(aCreature);
                    }
                }
            }

            //jesli jednostka nie umarła podczas ruchu to sprawdzane jest ostatnie pole
            if (!aCreature.isAlive()) {
                return;
            }
            move0(aCreature, aBattlePoint);
        }
    }

//        //nowa wersja
        void move0(final Creature aCreature, final BattlePoint aPoint) {
            if (canMove(aCreature, aPoint)) {
                if (mapWithSpecialFields.containsKey(aPoint)) {
                    SpecialField tile = mapWithSpecialFields.get(aPoint);
                    tile.doSomething(aCreature);
                }
                map.inverse()
                        .remove(aCreature);
                map.put(aPoint, aCreature);
//                aCreature.setCurrentPoint(aPoint);

            }
        }

    boolean canMove(final Creature aCreature, final BattlePoint aBattlePoint) {
        if (map.containsKey(aBattlePoint)) {
            return false;
        }
        if (mapWithSpecialFields.containsKey(aBattlePoint)) {
            mapWithSpecialFields.get(aBattlePoint).canInteract(aCreature);
        }
        final BattlePoint oldPosition = getPosition(aCreature);
        return aBattlePoint.distance(oldPosition.getX(), oldPosition.getY()) < aCreature.getMoveRange();
    }

    BattlePoint getPosition(Creature aCreature) {
        return map.inverse()
                .get(aCreature);
    }

    public BiMap<BattlePoint, SpecialField> getSpecialFields() {
        return mapWithSpecialFields;
    }

    void interact(Creature aCurrentCreature, BattlePoint aCurrentBattlePoint) {
        if (mapWithSpecialFields.containsKey(aCurrentBattlePoint)) {
            SpecialField tile = mapWithSpecialFields.get(aCurrentBattlePoint);
            tile.doSomething(aCurrentCreature);
        }
    }

    public void removeCreature(Creature creature) {
        map.inverse().remove(creature);
    }

    //Metoda ma na celu określenie trasy po której nastąpił ruch,
    public List<BattlePoint> examinePath(BattlePoint start, BattlePoint end) {

        List<BattlePoint> path = new ArrayList<>();

        //zmienne określające kierunek w zależności od pozycji
        int dx = Integer.signum(end.getX() - start.getX());
        int dy = Integer.signum(end.getY() - start.getY());


        //współrzędne startowe
        int x = start.getX();
        int y = start.getY();


        while (x != end.getX() || y != end.getY()) {
            if (x != end.getX()) x += dx;
            if (y != end.getY()) y += dy;
            path.add(new BattlePoint(x, y));
        }

        return path;
    }
}
