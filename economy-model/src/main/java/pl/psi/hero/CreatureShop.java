package pl.psi.hero;

import pl.psi.creatures.EconomyCreature;

public class CreatureShop
{

    public void buy( final EconomyHero aHero, final EconomyCreature aEconomyCreature )
    {
        aHero.substractGold( aEconomyCreature.getGoldCost() * aEconomyCreature.getAmount() );
        try
        {
            aHero.addCreature( aEconomyCreature );
        }
        catch( final Exception e )
        {
            aHero.addGold( aEconomyCreature.getGoldCost() * aEconomyCreature.getAmount() );
            throw new IllegalStateException( "hero cannot consume more creature" );
        }
    }
}
