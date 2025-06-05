package pl.psi.creatures;//  ******************************************************************

//
//  Copyright 2022 PSI Software AG. All rights reserved.
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
//  ******************************************************************

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import lombok.AccessLevel;
import lombok.Setter;
import pl.psi.Hero;
import pl.psi.Spells.ActiveSpellEffect;
import pl.psi.Spells.BuffSpell;
import pl.psi.Spells.Spell;
import pl.psi.TurnQueue;

import com.google.common.collect.Range;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Creature implements PropertyChangeListener {
    private CreatureStatisticIf stats;
    private CreatureStatisticIf originalStats;
    @Setter
    private int amount;


    @Getter
    @Setter(AccessLevel.PROTECTED)
    private int currentHp;
    private int counterAttackCounter = 1;
    private DamageCalculatorIf calculator;
    private final List<ActiveSpellEffect> activeSpellEffects = new ArrayList<>();
    private float reduceDemegeFactor;

    Creature() {
    }

    private Creature(final CreatureStatisticIf aStats, final DamageCalculatorIf aCalculator,
                     final int aAmount ) {
        stats = aStats;
        amount = aAmount;
        currentHp = stats.getMaxHp();
        calculator = aCalculator;
        reduceDemegeFactor = 1;
        this.originalStats = aStats;
    }

    public void attack(final Creature aDefender) {
        if (isAlive()) {
            final int damage = getCalculator().calculateDamage(this, aDefender);
            applyDamage(aDefender, damage);
            if (canCounterAttack(aDefender)) {
                counterAttack(aDefender);
            }
        }
    }

    public boolean isAlive() {
        return getAmount() > 0;
    }

    public void applyDamage(final Creature aDefender, final int aDamage) {
        int hpToSubstract = aDamage % aDefender.getMaxHp();
        int amountToSubstract = Math.round(aDamage / aDefender.getMaxHp());

        int hp = aDefender.getCurrentHp() - hpToSubstract;
        if (hp <= 0) {
            aDefender.setCurrentHp(aDefender.getMaxHp() - hp);
            aDefender.setAmount(aDefender.getAmount() - 1);
        }
        else{
            aDefender.setCurrentHp(hp);
        }
        aDefender.setAmount(aDefender.getAmount() - amountToSubstract * (int) Math.ceil(1-reduceDemegeFactor));
    }

    public int getMaxHp() {
        return stats.getMaxHp();
    }

    private boolean canCounterAttack(final Creature aDefender) {
        return aDefender.getCounterAttackCounter() > 0 && aDefender.getCurrentHp() > 0;
    }

    private void counterAttack(final Creature aAttacker) {
        final int damage = aAttacker.getCalculator()
                .calculateDamage(aAttacker, this);
        applyDamage(this, damage);
        aAttacker.counterAttackCounter--;
    }

    Range<Integer> getDamage() {
        return stats.getDamage();
    }

    public int getAttack() {
        return stats.getAttack();
    }


    public void applyTemporaryBuff(BuffSpell buffSpell) {
        this.getActiveSpellEffects().add(new ActiveSpellEffect(buffSpell, buffSpell.getDuration()));

        CreatureStatisticIf modifiedStats;
        if (originalStats instanceof CreatureStats) {
            modifiedStats = new CreatureStats((CreatureStats) originalStats);
        } else {
            modifiedStats = originalStats; // ewentualnie obsłuż inaczej lub rzuć wyjątek
        }

        for (ActiveSpellEffect effect : activeSpellEffects) {
            modifiedStats = effect.getSpell().modifyStats(modifiedStats);
        }
        this.stats = modifiedStats;
    }


    public int getArmor() {
        return stats.getArmor();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (TurnQueue.END_OF_TURN.equals(evt.getPropertyName())) {
            counterAttackCounter = 1;
            updateActiveSpells();
        }
    }

    private void updateActiveSpells() {
        Iterator<ActiveSpellEffect> iterator = activeSpellEffects.iterator();
        while (iterator.hasNext()) {
            ActiveSpellEffect effect = iterator.next();
            effect.decreaseDuration();
            if (effect.isExpired()) {
                iterator.remove();
            }
        }

        CreatureStatisticIf modifiedStats;
        if (originalStats instanceof CreatureStats) {
            modifiedStats = new CreatureStats((CreatureStats) originalStats);
        } else {
            modifiedStats = originalStats;
        }

        for (ActiveSpellEffect effect : activeSpellEffects) {
            modifiedStats = effect.getSpell().modifyStats(modifiedStats);
        }
        this.stats = modifiedStats;
    }


    protected void restoreCurrentHpToMax() {
        currentHp = stats.getMaxHp();
    }

    public String getName() {
        return stats.getName();
    }

    public int getMoveRange() {
        return stats.getMoveRange();
    }

    public void applyMagicDamage(Spell aDamageSpell) {
        if (isAlive()) {
            final int magicDamage = getCalculator().calculateMagicDamage(this, aDamageSpell);
            applyDamage(this, magicDamage);
        }
    }


    public static class Builder {
        private int amount = 1;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private CreatureStatisticIf statistic;

        public Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }
        public  Builder reduceDemegeFactor(float aReduceDemegeFactor) {
            return this;
        }

        public Builder calculator(final DamageCalculatorIf aCalc) {
            calculator = aCalc;
            return this;
        }

        public Creature build() {
            return new Creature(statistic, calculator, amount);
        }
    }

    @Override
    public String toString() {
        return getName() + System.lineSeparator() + getAmount();
    }
}
