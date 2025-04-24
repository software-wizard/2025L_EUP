package pl.psi.hero.skills;

import pl.psi.creatures.EconomyCreature;

public class OffenceSkill extends AbstractSkill {
    private final float attackBonus;

    public OffenceSkill( final String aName, final String aLevel, final float aAttackBonus )
    {
        super( aName, aLevel );
        attackBonus= aAttackBonus;
    }

    @Override
    public void apply( final EconomyCreature creature )
    {
        creature.getStats().setAttack(creature.getStats().getAttack()*(int) Math.ceil(1+attackBonus));
    }
}
