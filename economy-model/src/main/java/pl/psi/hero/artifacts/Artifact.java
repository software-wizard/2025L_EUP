package pl.psi.hero.artifacts;

import lombok.Getter;
import pl.psi.hero.Statistics;

public abstract class Artifact {
    @Getter
    private final int position;
    private final String rarity;
    private String name;

    Artifact(String name, String rarity, int position) {
        this.name = name;
        this.rarity = rarity;
        this.position = position;
    }

    public abstract void applyEffect();
}
