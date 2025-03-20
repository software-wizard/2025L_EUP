package pl.psi;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Point {
    private final int x;
    private final int y;

    Point(int aX, int aY) {
        x = aX;
        y = aY;
    }

    @Override
    public boolean equals(Object aO) {
        if (this == aO) return true;
        if (aO == null || getClass() != aO.getClass()) return false;
        Point point = (Point) aO;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public double distance(Point aOther) {
        return Math.sqrt((x - aOther.getX()) * (x - aOther.getX()) + (y - aOther.getY()) * (y - aOther.getY()));
    }
}
