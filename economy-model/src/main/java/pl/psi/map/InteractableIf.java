package pl.psi.map;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;

public interface InteractableIf {
    void interact(EconomyHero hero, BoardEconomy board, Point point);
}
