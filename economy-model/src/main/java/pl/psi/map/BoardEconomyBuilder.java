package pl.psi.map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.BuildingIf;
import pl.psi.economy.Point;

import java.util.Map;

public class BoardEconomyBuilder {
    private static final int MAX_WIDTH = 14;
    private final BiMap<Point, EconomyHero> heroMap = HashBiMap.create();
    private final BiMap<Point, MapObjectIf> interactionMap = HashBiMap.create();

    public BoardEconomyBuilder addHero(EconomyHero hero, final Point point) {
        heroMap.put(point, hero);
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
