package pl.psi;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    Map<Point, Creature> map = new HashMap<>();

    public Board(List<Creature> aC1, List<Creature> aC2) {
        for (int i = 0; i < aC1.size(); i++) {
            map.put(new Point(0, 1 + 2 * i), aC1.get(i));
        }

        for (int i = 0; i < aC2.size(); i++) {
            map.put(new Point(14, 1 + 2 * i), aC2.get(i));
        }

    }

    Creature get(Point aPoint) {
        return map.get(aPoint);
    }

    void move(Creature aC1, Point aPoint) {
        Point previousPoint = getPosition(aC1);
        boolean canMove = canMove(aC1, aPoint, previousPoint);
        if (canMove) {
            throw new IllegalArgumentException();
        }

        map.put(aPoint, aC1);
        map.remove(previousPoint);
    }

    public boolean canMove(Creature aC1, Point aPoint, Point previousPoint) {
        double distance = previousPoint.distance(aPoint);
        return aC1.getMoveRange() > distance;
    }

    Point getPosition(Creature aC1) {
        Point previousPoint = map.entrySet().stream()
                .filter(e -> e.getValue().equals(aC1))
                .findAny()
                .map(e -> e.getKey())
                .orElse(null);
        return previousPoint;
    }
}
