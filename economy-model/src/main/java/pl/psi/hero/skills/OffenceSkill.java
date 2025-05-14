package pl.psi.hero.skills;

import pl.psi.creatures.EconomyCreature;

public class OffenceSkill extends AbstractSkill {
    private final float attackBonus;

    public OffenceSkill( final String aLevel)
    {
        super( aLevel );
        if (aLevel.equals("Basic")) {
            attackBonus = 0.1f;
        } else if (aLevel.equals("Advanced")) {
            attackBonus = 0.2f;
        } else if (aLevel.equals("Expert")) {
            attackBonus = 0.3f;
        } else {
            throw new IllegalArgumentException("Invalid level: " + aLevel);
        }
    }

    @Override
    public void apply( final EconomyCreature creature )
    {
        creature.getStats().setAttack(creature.getStats().getAttack()*(int) Math.ceil(1+attackBonus));
    }
}
