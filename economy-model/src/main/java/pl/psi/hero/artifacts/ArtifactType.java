package pl.psi.hero.artifacts;

import lombok.Getter;
import pl.psi.hero.Statistics;

public enum ArtifactType {
    SWORD_OF_HELLFIRE("Sword of Hellfire", "/objects/Artifact_Sword_of_Hellfire.gif", new Statistics(6, 0, 0, 0), 6000),
    CENTAURS_AXE("Centaur's Axe", "/objects/Artifact_Centaur's_Axe.gif", new Statistics(2, 0, 0, 0), 2000),
    SHIELD_OF_THE_DAMNED("Shield of the Damned", "/objects/Artifact_Shield_of_the_Damned.gif", new Statistics(0, 6, 0, 0), 6000),
    BREASTPLATE_OF_PETRIFIED_WOOD("Breastplate of Petrified Wood", "/objects/Artifact_Breastplate_of_Petrified_Wood.gif", new Statistics(0, 0, 1, 0), 1000),
    HELM_OF_THE_ALABASTER_UNICORN("Helm of the Alabaster Unicorn", "/objects/Artifact_Helm_of_the_Alabaster_Unicorn.gif", new Statistics(0, 0, 0, 1), 1000),
    DRAGON_SCALE_ARMOR("Dragon Scale Armor", "/objects/Artifact_Dragon_Scale_Armor.gif", new Statistics(4, 4, 0, 0), 8000),
    CELESTIAL_NECKLACE_OF_BLISS("Celestial Necklace of Bliss", "/objects/Artifact_Celestial_Necklace_of_Bliss.gif", new Statistics(3, 3, 3, 3), 12000),
    CROWN_OF_DRAGONTOOTH("Crown of Dragontooth", "/objects/Artifact_Crown_of_Dragontooth.gif", new Statistics(0, 0, 4, 4), 8000),
    SANDALS_OF_THE_SAINT("Sandals of the Saint", "/objects/Artifact_Sandals_of_the_Saint.gif", new Statistics(2, 2, 2, 2), 8000),
    DRAGON_WING_TABARD("Dragon Wing Tabard", "/objects/Artifact_Dragon_Wing_Tabard.gif", new Statistics(0, 0, 2, 2), 4000),
    NECKLACE_OF_DRAGONTEETH("Necklace of Dragonteeth", "/objects/Artifact_Necklace_of_Dragonteeth.gif", new Statistics(0, 0, 3, 3), 6000),
    SKULL_HELMET("Skull Helmet", "/objects/Artifact_Skull_Helmet.gif", new Statistics(0, 0, 0, 2), 3000),
    RIB_CAGE("Rib Cage", "/objects/Artifact_Rib_Cage.gif", new Statistics(0, 0, 2, 0), 3000),
    DRAGONBONE_GREAVES("Dragonbone Greaves", "/objects/Artifact_Dragonbone_Greaves.gif", new Statistics(0, 0, 1, 1), 2000),
    STILL_EYE_OF_THE_DRAGON("Still Eye of the Dragon", "/objects/Artifact_Still_Eye_of_the_Dragon.gif", new Statistics(0, 0, 0, 0), 2000), // +1 Luck, +1 Morale
    QUIET_EYE_OF_THE_DRAGON("Quiet Eye of the Dragon", "/objects/Artifact_Quiet_Eye_of_the_Dragon.gif", new Statistics(1, 1, 0, 0), 2000),
    // Add more artifacts as needed

    ;




    @Getter
    private final String imagePath;
    @Getter
    private final Statistics statistics;
    @Getter
    private final String name;
    @Getter
    private final int cost;

    ArtifactType(String name, String imagePath, Statistics stats, int cost) {
        this.name = name;
        this.imagePath = imagePath;
        this.statistics = stats;
        this.cost = cost;
    }
}
