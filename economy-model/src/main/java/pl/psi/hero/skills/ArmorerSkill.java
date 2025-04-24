package pl.psi.hero.skills;

import pl.psi.creatures.EconomyCreature;

public class ArmorerSkill extends AbstractSkill {
    private final float reducedDamageFactor;

    public ArmorerSkill( final String aName, final String aLevel, final float aReducedDamageFactor )
    {
        super( aName, aLevel );
        reducedDamageFactor= aReducedDamageFactor;
    }

    @Override
    public void apply( final EconomyCreature creature )
    {
        creature.setReduceDemegeFactor(reducedDamageFactor);
    }
}