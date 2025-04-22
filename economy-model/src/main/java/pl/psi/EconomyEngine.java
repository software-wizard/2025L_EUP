package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.CreatureShop;
import pl.psi.hero.EconomyHero;
import pl.psi.map.resources.Resources;

public class EconomyEngine
{
    public static final String HERO_BOUGHT_CREATURE = "HERO_BOUGHT_CREATURE";
    public static final String ACTIVE_HERO_CHANGED = "ACTIVE_HERO_CHANGED";
    public static final String NEXT_ROUND = "NEXT_ROUND";
    private final EconomyHero hero1;
    private final EconomyHero hero2;
    private final CreatureShop creatureShop = new CreatureShop();
    private final PropertyChangeSupport observerSupport;
    private EconomyHero activeHero;
    private int roundNumber;
    private Resources resourcesToAdd;

    public EconomyEngine( final EconomyHero aHero1, final EconomyHero aHero2 )
    {
        hero1 = aHero1;
        hero2 = aHero2;
        activeHero = hero1;
        roundNumber = 1;
        observerSupport = new PropertyChangeSupport( this );
    }

    public void buy( final EconomyCreature aEconomyCreature )
    {
        creatureShop.buy( activeHero, aEconomyCreature );
        observerSupport.firePropertyChange( HERO_BOUGHT_CREATURE, null, null );
    }

    public EconomyHero getHero()
    {
        return activeHero;
    }

    private void endTurn()
    {

        resourcesToAdd = new Resources( 2000 * roundNumber ,0,0,0,0,0,0);
        roundNumber += 1;

        hero1.addResource(resourcesToAdd);
        hero2.addResource(resourcesToAdd);
        observerSupport.firePropertyChange( NEXT_ROUND, roundNumber - 1, roundNumber );
    }

    public int getRoundNumber()
    {
        return roundNumber;
    }

    public void addObserver( final String aPropertyName, final PropertyChangeListener aObserver )
    {
        observerSupport.addPropertyChangeListener( aPropertyName, aObserver );
    }

    public EconomyHero getPlayer1()
    {
        // TODO make copy
        return hero1;
    }

    public EconomyHero getPlayer2()
    {
        // TODO make copy
        return hero2;
    }
}
