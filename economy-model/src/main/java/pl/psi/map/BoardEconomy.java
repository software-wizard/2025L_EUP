package pl.psi.map;

import com.google.common.base.internal.Finalizer;
import com.google.common.collect.BiMap;

import java.util.Map;
import java.util.Optional;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.BuildingIf;
import pl.psi.map.buildings.EnterAction;

public class BoardEconomy {
    private static final int MAX_WIDTH = 14;
    private final BiMap<Point, EconomyHero> map;
    private final BiMap<Point, MapObjectIf> interactionMap;

    public BoardEconomy(BiMap<Point, EconomyHero> initialMap, BiMap<Point, MapObjectIf> interactionMap) {
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

    public Optional<MapObjectIf> getObjectAt(final Point point) {
        Object obj = interactionMap.get(point);
        if (obj instanceof MapObjectIf mapObj) {
            return Optional.of(mapObj);
        }
        return Optional.empty();
    }

    public Optional<InteractableIf> getInteractableAt(final Point point) {
        Object obj = interactionMap.get(point);
        if (obj instanceof InteractableIf interactable) {
            return Optional.of((interactable) );
        }
        return Optional.empty();
    }

    public Optional<BuildingIf> getBuildingAt(final Point point) {
        MapObjectIf obj = interactionMap.get(point);
        if (obj instanceof BuildingIf) {
            return Optional.of((BuildingIf) obj);
        }
        return Optional.empty();
    }


    public boolean canMove(final EconomyHero hero, final Point targetPoint) {
        Object obj = map.get(targetPoint);
        Object objOnInteractionMap = interactionMap.get(targetPoint);

        if (objOnInteractionMap instanceof MapObjectIf) {
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
                map.put(targetPoint, hero);
                hero.deductMove(distance);
            }
        }
    }


    public void interact(final EconomyHero hero, final Point targetPoint){
        MapObjectIf obj = interactionMap.get(targetPoint);
        if (obj instanceof InteractableIf interactable) {
            interactable.interact(hero, this, targetPoint);
        }
    }
    public EnterAction enter(final EconomyHero hero, final Point targetPoint){
        MapObjectIf obj = interactionMap.get(targetPoint);
        if (obj instanceof BuildingIf building){
            return building.onEnter();
        }
        return null;
    }

    public EnterAction secondInteraction(final EconomyHero hero, final Point targetPoint){
        MapObjectIf obj = interactionMap.get(targetPoint);
        if(obj instanceof BuildingIf building){
            return building.secondInteraction();
        }
        return null;
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
