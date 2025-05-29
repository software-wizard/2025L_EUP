package pl.psi.hero.skills;

public class OffenceSkill extends AbstractSkill {
    private float attackBonus;

    public OffenceSkill( )
    {
        super();
        attackBonus = 0.1f;
    }

    @Override
    public void upgrade()
    {
        if ( this.level.equals( SkillLevel.BASIC) )
        {
            this.level = SkillLevel.ADVANCED;
            attackBonus= 0.2f;
        }
        else if ( this.level.equals( SkillLevel.ADVANCED) )
        {
            this.level = SkillLevel.EXPERT;
            attackBonus= 0.3f;
        }
        else
        {
            throw new IllegalStateException( "Cannot upgrade from Expert level." );
        }
    }
    @Override
    public SkillName getName() {return SkillName.OFFENCE;}
    @Override
    public float getFactor() {return attackBonus; }

}
