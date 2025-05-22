package pl.psi.hero.skills;

import pl.psi.creatures.EconomyCreature;

public class ArmorerSkill extends AbstractSkill {
    private float reducedDamageFactor;

    public ArmorerSkill()
    {
        super();
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
        if ( this.level.equals( SkillLevel.Basic ) )
        {
            this.level = SkillLevel.Advanced;
            this.reducedDamageFactor= 0.2f;
        }
        else if ( this.level.equals( SkillLevel.Advanced) )
        {
            this.level = SkillLevel.Expert;
            this.reducedDamageFactor= 0.3f;
        }
        else
        {
            throw new IllegalStateException( "Cannot upgrade from Expert level." );
        }
    }
    @Override
    public SkillName getName() {return SkillName.Armorer;}
}