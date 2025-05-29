package pl.psi.creatures;

import java.util.Random;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class DefaultDamageCalculator extends AbstractCalculateDamageStrategy {
    public DefaultDamageCalculator(final Random aRand) {
        super(aRand);
    }


    public DefaultDamageCalculator() {
        super(new Random());
    }
}
