package pl.psi.map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.Optional;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.resources.ResourceGenIf;

public class BoardEconomy {
    private static final int MAX_WIDTH = 14;
    private final BiMap<Point, Object> map;
    private final BiMap<Point, Object> interactionMap;

    public BoardEconomy(BiMap<Point, Object> initialMap, BiMap<Point, Object> interactionMap) {
        this.map = initialMap; //could be even hero map
        this.interactionMap = interactionMap; //map for interactables
    }

    public Optional<EconomyHero> getHero(final Point point) {
        Object obj = map.get(point);
        if (obj instanceof EconomyHero hero) {
            return Optional.of(hero);
        }
        return Optional.empty();
    }

    public Optional<InteractableIf> getInteractableAt(Point point) {
        Object obj = interactionMap.get(point);
        if (obj instanceof InteractableIf interactable) {
            return Optional.of((interactable) );
        }
        return Optional.empty();
    }

    public boolean canMove(final EconomyHero hero, final Point targetPoint) {
        Object obj = map.get(targetPoint);

        if (obj instanceof InteractableIf) {
            return true;
        }
        if (obj instanceof EconomyHero) {
            return false;
        }
        final Point oldPosition = getPosition(hero);
        return targetPoint.distance(oldPosition.getX(), oldPosition.getY()) < hero.getMoveRange();
    }

    public void move(final EconomyHero hero, final Point targetPoint) {
        if (canMove(hero, targetPoint)) {
            map.inverse().remove(hero);
            Object obj = interactionMap.get(targetPoint);
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
        interactionMap.remove(point);
    }

    public static BoardEconomyBuilder builder() {
        return new BoardEconomyBuilder();
    }
}
