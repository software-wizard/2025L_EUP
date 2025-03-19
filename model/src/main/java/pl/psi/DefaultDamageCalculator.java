package pl.psi;

public class DefaultDamageCalculator implements DamageCalculatorIf{
    public static final double ATTACK_BONUS = 0.05;
    public static final double DEFENCE_BONUS = 0.025;

    @Override
    public int calculate(Creature aAttacker, Creature aDefender) {
        int baseDamage = aAttacker.getDamage().lowerEndpoint() * aAttacker.getAmount();
        int d = aAttacker.getAttack() - getDefence(aDefender);
        if (d > 60) {
            d = 60;
        } else if (d < -40) {
            d = -40;
        }

        int totalDamage;
        if (d >= 0) {
            totalDamage = (int) (baseDamage * (1 + ATTACK_BONUS * d));
        } else {
            totalDamage = (int) (baseDamage * (1 + DEFENCE_BONUS * d));
        }
        return totalDamage;
    }

    protected int getDefence(Creature aDefender) {
        return aDefender.getDefence();
    }
}
