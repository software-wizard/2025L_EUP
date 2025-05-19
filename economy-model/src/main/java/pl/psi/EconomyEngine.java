package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.CreatureShop;
import pl.psi.hero.EconomyHero;
import pl.psi.map.resources.Resources;

public class EconomyEngine {
    public static final String HERO_BOUGHT_CREATURE = "HERO_BOUGHT_CREATURE";
    private final EconomyHero hero1;
    private final CreatureShop creatureShop = new CreatureShop();
    private final PropertyChangeSupport observerSupport;

    public EconomyEngine(final EconomyHero aHero1) {
        hero1 = aHero1;
        observerSupport = new PropertyChangeSupport(this);
    }

    public void buy(final EconomyCreature aEconomyCreature) {
        creatureShop.buy(hero1, aEconomyCreature);
        observerSupport.firePropertyChange(HERO_BOUGHT_CREATURE, null, null);
    }

    public EconomyHero getHero() {
        return hero1;
    }

    public void addObserver(final String aPropertyName, final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aPropertyName, aObserver);
    }
}
