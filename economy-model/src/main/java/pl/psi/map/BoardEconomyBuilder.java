package pl.psi.map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.BuildingIf;

public class BoardEconomyBuilder {
    private static final int MAX_WIDTH = 14;
    private final BiMap<Point, Object> tempMap = HashBiMap.create();
    private final BiMap<Point, InteractableIf> interactionMap = HashBiMap.create();
    private final BiMap<Point, BuildingIf> buildingMap = HashBiMap.create();

    public BoardEconomyBuilder addHero(EconomyHero hero, int xPosition) {
        tempMap.put(new Point(xPosition, 1), hero);
        return this;
    }

    public BoardEconomyBuilder addInteractables(Map<Point, InteractableIf> interactables) {
        interactables.forEach((point, interactable) -> interactionMap.putIfAbsent(point, interactable));
        return this;
    }

    public BoardEconomyBuilder addBuildings(Map<Point, BuildingIf> buildings) {
        buildings.forEach((point, building) -> buildingMap.putIfAbsent(point, building));
        return this;
    }



    public BoardEconomy build() {
        return new BoardEconomy(tempMap, interactionMap);
    }
}
