package pl.psi.map.resources.generators;

import lombok.Getter;
import pl.psi.map.resources.Resources;

@Getter
public enum ResourceGenType {
    CRYSTAL("/objects/crystal_mine.jpg", new Resources(0, 0, 0, 0, 0, 1, 0)),
    GEM("/objects/gem_pond.jpg", new Resources(0, 0, 0, 0, 0, 0, 1)),
    GOLD("/objects/gold_mine.jpg", new Resources(1000, 0, 0, 0, 0, 0, 0)),
    MERCURY("/objects/mercury_lab.jpg", new Resources(0, 0, 0, 1, 0, 0, 0)),
    ORE("/objects/ore_deposits.jpg", new Resources(0, 0, 2, 0, 0, 0, 0)),
    SULFUR("/objects/sulfur_mound.jpg", new Resources(0, 0, 0, 0, 0, 0, 1)),
    WOOD("/objects/sawmill.jpg", new Resources(0, 2, 0, 0, 0, 0, 0)),;


    private final String imagePath;
    private final Resources resources;

    ResourceGenType(String imagePath, Resources resources) {
        this.imagePath = imagePath;
        this.resources = resources;
    }

}
