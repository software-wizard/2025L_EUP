package pl.psi.hero.skills;

public class ArmorerSkill extends AbstractSkill {
    private float reducedDamageFactor;

    public ArmorerSkill()
    {
        super();
        this.reducedDamageFactor = 0.1f;
    }

    @Override
    public void upgrade()
    {
        if ( this.level.equals( SkillLevel.BASIC) )
        {
            this.level = SkillLevel.ADVANCED;
            this.reducedDamageFactor= 0.2f;
        }
        else if ( this.level.equals( SkillLevel.ADVANCED) )
        {
            this.level = SkillLevel.EXPERT;
            this.reducedDamageFactor= 0.3f;
        }
        else
        {
            throw new IllegalStateException( "Cannot upgrade from Expert level." );
        }
    }
    @Override
    public SkillName getName() {return SkillName.ARMORER;}

    @Override
    public float getFactor() { return reducedDamageFactor;}
}