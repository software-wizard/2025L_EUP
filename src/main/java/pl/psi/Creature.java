package pl.psi;

import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Creature {

    private final int maxHp;
    private int amount;
    private final int attack;
    private final int defence;
    private final Range<Integer> damage;
    private int currentHp;
    @Setter
    private DamageCalculatorIf damageCalculator;

    Creature(int aMaxHp, int aAmount, int aAttack, int aDefence, Range<Integer> aDamage) {
        maxHp = aMaxHp;
        amount = aAmount;
        attack = aAttack;
        defence = aDefence;
        damage = aDamage;
        currentHp = maxHp;
        damageCalculator = new DefaultDamageCalculator();
    }

    void attack(Creature aDefender) {
        int totalDamage = damageCalculator.calculate(this, aDefender);
        applyDamage(aDefender, totalDamage);
    }

    private void applyDamage(Creature aDefender, int totalDamage) {
        int amountToSub = totalDamage / aDefender.maxHp;
        int hpToSub = totalDamage - amountToSub * aDefender.maxHp;

        aDefender.amount = aDefender.amount - amountToSub;
        aDefender.currentHp = aDefender.currentHp - hpToSub;
    }


}
