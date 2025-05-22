package pl.psi.hero.skills;

import pl.psi.creatures.EconomyCreature;

public class ArmorerSkill extends AbstractSkill {
    private float reducedDamageFactor;

    public ArmorerSkill()
    {
        this.level = "Basic";
        this.reducedDamageFactor = 0.1f;
    }

    @Override
    public void apply( final EconomyCreature creature )
    {
        creature.setReduceDamageFactor(creature.getReduceDamageFactor()+reducedDamageFactor);
    }
    @Override
    public void upgrade()
    {
        if ( this.level.equals( "Basic" ) )
        {
            this.level = "Advanced";
            this.reducedDamageFactor= 0.2f;
        }
        else if ( this.level.equals( "Advanced" ) )
        {
            this.level = "Expert";
            this.reducedDamageFactor= 0.3f;
        }
        else
        {
            throw new IllegalStateException( "Cannot upgrade from Expert level." );
        }
    }
    @Override
    public String getName() {return "Armorer";}
}