package pl.psi.creatures;

import lombok.Getter;
import lombok.Setter;

public class EconomyCreature
{
    @Setter
    @Getter
    private final CreatureStatistic stats;
    private final int amount;
    private final int goldCost;
    private final float reduceDemegeFactor;

    EconomyCreature( final CreatureStatistic aStats, final int aAmount, final int aGoldCost , float aReducedDamageFactor )
    {
        stats = aStats;
        amount = aAmount;
        goldCost = aGoldCost;
        reduceDemegeFactor=aReducedDamageFactor;
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

    public float getReduceDemegeFactor() {
        return reduceDemegeFactor;
    }
    public void setReduceDemegeFactor(float aReduceDemegeFactor)
    {
        reduceDemegeFactor = aReduceDemegeFactor;
    }
}
