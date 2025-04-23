package pl.psi.map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.BuildingIf;

public class BoardEconomyBuilder {
    private static final int MAX_WIDTH = 14;
    private final BiMap<Point, EconomyHero> heroMap = HashBiMap.create();
    private final BiMap<Point, MapObjectIf> interactionMap = HashBiMap.create();

    public BoardEconomyBuilder addHero(EconomyHero hero, int xPosition) {
        heroMap.put(new Point(xPosition, 1), hero);
        return this;
    }

    public BoardEconomyBuilder addInteractables(Map<Point, MapObjectIf> interactables) {
        interactables.forEach((point, interactable) -> interactionMap.putIfAbsent(point, interactable));
        return this;
    }

    public BoardEconomyBuilder addBuildings(Map<Point, BuildingIf> buildings) {
        buildings.forEach((point, building) -> interactionMap.putIfAbsent(point, building));
        return this;
    }



    public BoardEconomy build() {
        return new BoardEconomy(heroMap, interactionMap);
    }
}
