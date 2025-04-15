package pl.psi.creatures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LuckCalculatorTest {

    @Test
    void shouldReturnApprox12_5PercentChanceForLuck3() {
        LuckCalculator calc = new LuckCalculator();
        int luckyHits = 0;
        int totalAttempts = 10_000;

        for (int i = 0; i < totalAttempts; i++) {
            if (calc.isLucky(3)) {
                luckyHits++;
            }
        }

        double probability = (double) luckyHits / totalAttempts;

        // 12.5% ±1%
        assertThat(probability).isBetween(0.115, 0.135);
    }

    @Test
    void shouldReturnFalseForLuck0() {
        LuckCalculator calc = new LuckCalculator();
        assertThat(calc.isLucky(0)).isFalse();
    }

    @Test
    void shouldReturnFalseForNegativeLuck() {
        LuckCalculator calc = new LuckCalculator();
        assertThat(calc.isLucky(-2)).isFalse();
    }
}
