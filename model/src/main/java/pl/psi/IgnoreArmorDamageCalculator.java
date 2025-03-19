package pl.psi;

public class IgnoreArmorDamageCalculator extends DefaultDamageCalculator {

    @Override
    protected int getDefence(Creature aDefender) {
        return 0;
    }
}
