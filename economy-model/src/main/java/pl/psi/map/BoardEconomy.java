package pl.psi.map;

import com.google.common.collect.BiMap;

import java.util.Optional;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.BuildingIf;

public class BoardEconomy {
    private static final int MAX_WIDTH = 14;
    private final BiMap<Point, Object> map;
    private final BiMap<Point, InteractableIf> interactionMap;
    private final BiMap<Point, BuildingIf> buildingMap;

    public BoardEconomy(BiMap<Point, Object> initialMap, BiMap<Point, InteractableIf> interactionMap, BiMap<Point,BuildingIf> initbuildingMap) {
        this.map = initialMap; //could be even hero map
        this.interactionMap = interactionMap; //map for interactables
        this.buildingMap = initbuildingMap;
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

    public Optional<BuildingIf> getBuildingAt(Point point) {
        Object obj = buildingMap.get(point);
        if (obj instanceof BuildingIf building) {
            return Optional.of(building);
        }
        return Optional.empty();
    }

    public boolean canMove(final EconomyHero hero, final Point targetPoint) {
        Object obj = map.get(targetPoint);

        if (obj instanceof InteractableIf) {
            return true;
        }
        if (obj instanceof BuildingIf){
            return true;
        }
        if (obj instanceof EconomyHero) {
            return false;
        }
        final Point oldPosition = getPosition(hero);
        double distance = targetPoint.distance(oldPosition.getX(), oldPosition.getY());
        return distance <= hero.getRemainingMoveRange();

    }

    public void move(final EconomyHero hero, final Point targetPoint) {
        if (canMove(hero, targetPoint)) {
            Point oldPosition = getPosition(hero);
            double distance = oldPosition.distance(targetPoint);

            if (hero.canMoveTo(distance)) {
                map.inverse().remove(hero);
                Object obj = interactionMap.get(targetPoint);
                if (obj instanceof InteractableIf interactable) {
                    interactable.interact(hero, this, targetPoint);
                }
                map.put(targetPoint, hero);
                hero.deductMove(distance);
            }
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
