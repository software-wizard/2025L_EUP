package pl.psi.creatures;

import lombok.Getter;

public class EconomyCreature
{
    @Getter
    private final CreatureStatistic stats;
    private final int amount;
    private final int goldCost;
    private float reduceDamageFactor;

    EconomyCreature( final CreatureStatistic aStats, final int aAmount, final int aGoldCost , float aReducedDamageFactor )
    {
        stats = aStats;
        amount = aAmount;
        goldCost = aGoldCost;
        reduceDamageFactor =aReducedDamageFactor;
    }

    public int getAmount()
    {
        return amount;
    }

    public int getGoldCost()
    {
        return goldCost;
    }

    public String getName()
    {
        return stats.getTranslatedName();
    }

    public boolean isUpgraded()
    {
        return stats.isUpgraded();
    }

    public int getTier()
    {
        return stats.getTier();
    }

    public float getReduceDamageFactor() {return reduceDamageFactor;}

    public void setReduceDamageFactor(float aReduceDemegeFactor) {
        reduceDamageFactor = aReduceDemegeFactor;}
}
