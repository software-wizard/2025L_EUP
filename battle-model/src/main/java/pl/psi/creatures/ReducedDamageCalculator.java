package pl.psi.creatures;

import pl.psi.Spells.Spell;

import java.util.Random;

public class ReducedDamageCalculator extends AbstractCalculateDamageStrategy{

    private final float reduceDamageFactor;
    private final float bonusAttackFactor;

    public ReducedDamageCalculator(float aReduceDamageFactor,float aBonusAttackFactor) {
        super(new Random());
        this.reduceDamageFactor = aReduceDamageFactor;
        this.bonusAttackFactor = aBonusAttackFactor;
    }

    @Override
    public int calculateDamage( final Creature aAttacker, final Creature aDefender )
    {
        final int armor = getArmor( aDefender );

        final int randValue = rand.nextInt( aAttacker.getDamage()
                .upperEndpoint()
                - aAttacker.getDamage()
                .lowerEndpoint()
                + 1 ) + aAttacker.getDamage()
                .lowerEndpoint();

        double oneCreatureDamageToDeal;
        if( aAttacker.getAttack() >= armor )
        {
            int attackPoints = aAttacker.getAttack() - armor;
            if( attackPoints > MAX_ATTACK_DIFF )
            {
                attackPoints = MAX_ATTACK_DIFF;
            }
            oneCreatureDamageToDeal = randValue * (1 + attackPoints * ATTACK_BONUS);
        }
        else
        {
            int defencePoints = armor - aAttacker.getAttack();
            if( defencePoints > MAX_DEFENCE_DIFF )
            {
                defencePoints = MAX_DEFENCE_DIFF;
            }
            oneCreatureDamageToDeal = randValue * (1 - defencePoints * DEFENCE_BONUS);
        }

        if( oneCreatureDamageToDeal < 0 )
        {
            oneCreatureDamageToDeal = 0;
        }
        return (int)(aAttacker.getAmount() * oneCreatureDamageToDeal * (1-reduceDamageFactor) * (1 + bonusAttackFactor));
    }

    @Override
    public int calculateMagicDamage(Creature aDefender, Spell aSpell) {
        final int armor = getArmor(aDefender);

        int minDamage;
        int maxDamage;

        switch (aSpell.getSpellLevel()) {
            case 1:
                minDamage = 10;
                maxDamage = 20;
                break;
            case 2:
                minDamage = 20;
                maxDamage = 30;
                break;
            case 3:
                minDamage = 30;
                maxDamage = 40;
                break;
            default:
                throw new IllegalArgumentException("Invalid spell level: " + aSpell.getSpellLevel());
        }

        int randValue = rand.nextInt(maxDamage - minDamage + 1) + minDamage;

        double reducedDamage = randValue * (1 - (armor * 0.025));
        if (reducedDamage < 0) {
            reducedDamage = 0;
        }

        return (int) (reducedDamage* (1-reduceDamageFactor) * (1 + bonusAttackFactor));
    }

}
