package pl.psi.map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.Optional;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;

public class BoardEconomy {
    private static final int MAX_WIDTH = 14;
    private final BiMap<Point, Object> map;

    public BoardEconomy(BiMap<Point, Object> initialMap) {
        this.map = initialMap;
    }

    public Optional<EconomyHero> getHero(final Point point) {
        Object obj = map.get(point);
        if (obj instanceof EconomyHero hero) {
            return Optional.of(hero);
        }
        return Optional.empty();
    }


    public boolean canMove(final EconomyHero hero, final Point targetPoint) {
        Object obj = map.get(targetPoint);
        if (obj instanceof InteractableIf) {
            return true;
        }
        final Point oldPosition = getPosition(hero);
        return targetPoint.distance(oldPosition.getX(), oldPosition.getY()) < hero.getMoveRange();
    }

    public void move(final EconomyHero hero, final Point targetPoint) {
        if (canMove(hero, targetPoint)) {
            map.inverse().remove(hero);
            Object obj = map.get(targetPoint);
            if (obj instanceof InteractableIf interactable) {
                interactable.interact(hero, this, targetPoint);
            }
            map.put(targetPoint, hero);
        }
    }

    public Point getPosition(EconomyHero hero) {
        return map.inverse().get(hero);
    }

    public void removeInteractableAt(Point point) {
        Object obj = map.get(point);
        if (obj instanceof InteractableIf) {
            map.remove(point);
        }
    }

    public InteractableIf getInteractableAt(Point point) {
        Object obj = map.get(point);
        if (obj instanceof InteractableIf) {
            return (InteractableIf) map.get(point);
        }
        return null;
    }

    public static BoardEconomyBuilder builder() {
        return new BoardEconomyBuilder();
    }
}
