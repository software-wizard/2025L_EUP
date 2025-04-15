package pl.psi.creatures;

import java.util.Random;

public class LuckCalculator {

    private static final Random RANDOM = new Random();

    public boolean isLucky(int luck) {
        if (luck <= 0) {
            return false;
        }

        double chance = 0;
        switch (luck) {
            case 1: chance = 0.042; break;
            case 2: chance = 0.083; break;
            case 3: chance = 0.125; break;
            default: chance = 0.0; break;
        }

        return RANDOM.nextDouble() < chance;
    }
}
