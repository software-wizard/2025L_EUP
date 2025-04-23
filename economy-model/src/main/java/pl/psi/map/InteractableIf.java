package pl.psi.map;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;

import java.util.Map;

public interface InteractableIf extends MapObjectIf {
    typeOfObject type = typeOfObject.PICKUPABLE;
    void interact(EconomyHero hero, BoardEconomy board, Point point);
}
