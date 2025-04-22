package pl.psi.map.resources.generators;

import pl.psi.EconomyEngine;
import pl.psi.hero.EconomyHero;

public interface ResourceGenIf {
    public void generateResource();
    String getPath();
    public EconomyHero getOwner();
}
