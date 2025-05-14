package pl.psi.hero.artifacts;

import lombok.Getter;
import pl.psi.hero.Statistics;

public enum ArtifactType {
    SWORD_OF_HELLFIRE("Sword of Hellfire", "/objects/Artifact_Sword_of_Hellfire.gif", new Statistics(6,0,0,0),6000),
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
        this.cost = cost
    }
}
