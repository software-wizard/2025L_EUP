package pl.psi.map.buildings.town;

import pl.psi.hero.EconomyHero;
import pl.psi.map.resources.Resources;

import java.util.Set;

public interface BuildingType {
    void applyEffect(Town town, EconomyHero hero);

    Resources getCost();
    boolean isBuiltIn(Town town);
    void buildIn(Town town, EconomyHero hero);
    Set<BuildingType> getPrerequisites();  // <-- add this
    void registerInTown(Town town);
}
