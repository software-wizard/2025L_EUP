package pl.psi.map;

import java.util.Optional;

import com.google.common.collect.BiMap;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.BuildingIf;
import pl.psi.map.buildings.enterAction.EnterAction;
import pl.psi.economy.Point;

import static pl.psi.map.MapObjectIf.typeOfObject.PICKUPABLE;

public class BoardEconomy {
    private final BiMap<Point, EconomyHero> map;
    private final BiMap<Point, MapObjectIf> interactionMap;

    public BoardEconomy(BiMap<Point, EconomyHero> initialMap, BiMap<Point, MapObjectIf> interactionMap) {
        this.map = initialMap; //could be even hero map
        this.interactionMap = interactionMap; //map for interactables
    }

    public Optional<EconomyHero> getHero(final Point point) {
        return Optional.ofNullable(map.get(point));
    }

    public Optional<MapObjectIf> getObjectAt(final Point point) {
        return Optional.ofNullable(interactionMap.get(point));
    }

    public Optional<InteractableIf> getInteractableAt(final Point point) {
        MapObjectIf obj = interactionMap.get(point);
        if (obj instanceof InteractableIf) {
            return Optional.of((InteractableIf) obj );
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

        if (objOnInteractionMap != null) {
            return true;
        }
        if (obj != null) {
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
            obj.interact(hero);
        if(obj.getTypeOfObject() == PICKUPABLE) {
            interactionMap.remove(targetPoint);
        }
    }

    public EnterAction enter(final Point targetPoint){
        MapObjectIf obj = interactionMap.get(targetPoint);
            return obj.onEnter();
    }

    public EnterAction secondInteraction(final Point targetPoint){
        MapObjectIf obj = interactionMap.get(targetPoint);
            return obj.secondInteraction();
    }


    public Point getPosition(EconomyHero hero) {
        return map.inverse().get(hero);
    }

    public static BoardEconomyBuilder builder() {
        return new BoardEconomyBuilder();
    }
}
