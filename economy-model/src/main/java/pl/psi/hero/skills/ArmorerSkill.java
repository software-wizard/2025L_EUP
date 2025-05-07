package pl.psi.hero.skills;

import pl.psi.creatures.EconomyCreature;

public class ArmorerSkill extends AbstractSkill {
    private final float reducedDamageFactor;

    public ArmorerSkill( final String aLevel )
    {
        super( aLevel );
        if ( aLevel.equals( "Basic" ) )
        {
            reducedDamageFactor = 0.1f;
        }
        else if ( aLevel.equals( "Advanced" ) )
        {
            reducedDamageFactor = 0.2f;
        }
        else if ( aLevel.equals( "Expert" ) )
        {
            reducedDamageFactor = 0.3f;
        }
        else
        {
            throw new IllegalArgumentException( "Invalid level: " + aLevel );
        }
    }

    @Override
    public void apply( final EconomyCreature creature )
    {
        creature.setReduceDamageFactor(creature.getReduceDamageFactor()+reducedDamageFactor);
    }
}