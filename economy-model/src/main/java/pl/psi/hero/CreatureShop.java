package pl.psi.hero;

import pl.psi.creatures.EconomyCreature;
import pl.psi.map.resources.Resources;

public class CreatureShop
{

    public void buy( final EconomyHero aHero, final EconomyCreature aEconomyCreature )
    {
        Resources cost = new Resources(aEconomyCreature.getGoldCost() * aEconomyCreature.getAmount(), 0,0,0,0,0,0);
        aHero.pay(cost);;
        try
        {
            aHero.addCreature( aEconomyCreature );
        }
        catch( final Exception e )
        {
            aHero.addResource(cost);
            throw new IllegalStateException( "hero cannot consume more creature" );
        }
    }
}
