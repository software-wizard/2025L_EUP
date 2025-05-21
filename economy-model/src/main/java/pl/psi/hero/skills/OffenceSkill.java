package pl.psi.hero.skills;

import pl.psi.creatures.EconomyCreature;

public class OffenceSkill extends AbstractSkill {
    private float attackBonus;

    public OffenceSkill( final String aLevel )
    {
        this.level = aLevel;
        attackBonus = 0.1f;
    }
    @Override
    public void apply( final EconomyCreature creature )
    {
        creature.getStats().setAttack(creature.getStats().getAttack()*(int) Math.ceil(1+attackBonus));
    }
    @Override
    public void upgrade()
    {
        if ( this.level.equals( "Basic" ) )
        {
            this.level = "Advanced";
            attackBonus= 0.2f;
        }
        else if ( this.level.equals( "Advanced" ) )
        {
            this.level = "Expert";
            attackBonus= 0.3f;
        }
        else
        {
            throw new IllegalStateException( "Cannot upgrade from Expert level." );
        }
    }
    @Override
    public String getName() {return "Offence";}
}
