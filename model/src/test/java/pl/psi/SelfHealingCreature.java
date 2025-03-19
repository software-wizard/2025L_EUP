package pl.psi;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;

public class SelfHealingCreature extends Creature {
    private final Creature decorated;

    public SelfHealingCreature(Creature aCreature) {
        super(0, 0, 0, 0, null, null);
        decorated = aCreature;
    }

    @Override
    public int getMaxHp() {
        return decorated.getMaxHp();
    }

    @Override
    public Integer getMoveRange() {
        return decorated.getMoveRange();
    }

    @Override
    public int getAmount() {
        return decorated.getAmount();
    }

    @Override
    public int getAttack() {
        return decorated.getAttack();
    }

    @Override
    public int getDefence() {
        return decorated.getDefence();
    }

    @Override
    public Range<Integer> getDamage() {
        return decorated.getDamage();
    }

    @Override
    public int getCurrentHp() {
        return decorated.getCurrentHp();
    }

    @Override
    public DamageCalculatorIf getDamageCalculator() {
        return decorated.getDamageCalculator();
    }

    @Override
    public void setDamageCalculator(DamageCalculatorIf damageCalculator) {
        decorated.setDamageCalculator(damageCalculator);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        decorated.propertyChange(evt);
        if (evt.getPropertyName().equals(TurnQueue.TURN_END)) {
            heal();
        }
    }
}
