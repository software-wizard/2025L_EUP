package pl.psi;

import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Getter
public class Creature implements PropertyChangeListener {

    private final int maxHp;
    private final Integer moveRange;
    private int amount;
    private final int attack;
    private final int defence;
    private final Range<Integer> damage;
    private int currentHp;
    @Setter
    private DamageCalculatorIf damageCalculator;

    Creature(int aMaxHp, int aAmount, int aAttack, int aDefence, Range<Integer> aDamage, Integer aMoveRange) {
        maxHp = aMaxHp;
        amount = aAmount;
        attack = aAttack;
        defence = aDefence;
        damage = aDamage;
        moveRange = aMoveRange;
        currentHp = maxHp;
        damageCalculator = new DefaultDamageCalculator();
    }

    void attack(Creature aDefender) {
        int totalDamage = damageCalculator.calculate(this, aDefender);
        applyDamage(aDefender, totalDamage);
    }

    private void applyDamage(Creature aDefender, int totalDamage) {
        int amountToSub = totalDamage / aDefender.getMaxHp();
        int hpToSub = totalDamage - amountToSub * aDefender.getMaxHp();

        aDefender.amount = aDefender.getAmount() - amountToSub;
        aDefender.currentHp = aDefender.getCurrentHp() - hpToSub;
    }


    protected void heal() {
        currentHp = maxHp;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
