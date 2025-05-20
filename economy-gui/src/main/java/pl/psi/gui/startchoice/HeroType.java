package pl.psi.gui.startchoice;

import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.Statistics;
import pl.psi.map.resources.Resources;

import java.util.List;
import java.util.function.Supplier;

public enum HeroType {

    SANDRO("Sandro", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(1, 0, 2, 1), List.of(
                factory.create(false, 1, 40)
        ));
    }),

    VIDOMINA("Vidomina", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(0, 0, 1, 2), List.of(
                factory.create(false, 1, 40)
        ));
    }),

    NIMBUS("Nimbus", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(0, 0, 2, 1), List.of(
                factory.create(false, 1, 40)
        ));
    }),

    THANT("Thant", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(0, 0, 1, 2), List.of(
                factory.create(false, 1, 40)
        ));
    }),

    XSI("Xsi", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(1, 0, 1, 1), List.of(
                factory.create(false, 1, 40)
        ));
    }),

    MOANDOR("Moandor", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(0, 0, 1, 1), List.of(
                factory.create(false, 1, 40)
        ));
    }),

    ISRA("Isra", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(1, 0, 1, 1), List.of(
                factory.create(false, 1, 40)
        ));
    }),

    CLAVIUS("Clavius", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(1, 0, 1, 1), List.of(
                factory.create(false, 1, 40)
        ));
    }),

    TAMIKA("Tamika", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(2, 1, 1, 1), List.of(
                factory.create(false, 1, 40),
                factory.create(true, 2, 6)
        ));
    }),

    GALTHRAN("Galthran", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(1, 2, 1, 1), List.of(
                factory.create(false, 1, 60)
        ));
    }),

    VOKIAL("Vokial", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(2, 1, 1, 1), List.of(
                factory.create(false, 1, 40),
                factory.create(true, 2, 6)
        ));
    }),

    STRAKER("Straker", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(1, 2, 1, 1), List.of(
                factory.create(true, 2, 6),
                factory.create(false, 3, 5)
        ));
    }),

    SEPTIENNA("Septienna", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(1, 0, 1, 2), List.of(
                factory.create(false, 1, 40)
        ));
    }),

    NAADIR("Naadir", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(1, 1, 1, 1), List.of(
                factory.create(false, 1, 40)
        ));
    }),

    FIONA("Fiona", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(1, 1, 1, 1), List.of(
                factory.create(false, 1, 40)
        ));
    }),

    CHARNA("Charna", EconomyHero.Fraction.NECROPOLIS, () -> {
        var factory = new EconomyNecropolisFactory();
        return new HeroData(new Statistics(2, 0, 1, 1), List.of(
                factory.create(false, 1, 40)
        ));
    });

    public static List<String> getHeroNamesForFraction(EconomyHero.Fraction fraction) {
        // Return hero names based on the fraction
        switch (fraction) {
            case NECROPOLIS:
                return List.of("Sandro", "Vidomina", "Nimbus", "Thant", "Xsi", "Moandor", "Isra", "Clavius", "Tamika", "Galthran", "Vokial", "Straker", "Septienna", "Naadir", "Fiona", "Charna");
            default:
                return List.of();  // Return an empty list for unknown fractions
        }
    }

    public final String displayName;
    public final EconomyHero.Fraction fraction;
    private final Supplier<HeroData> dataSupplier;

    HeroType(String displayName, EconomyHero.Fraction fraction, Supplier<HeroData> dataSupplier) {
        this.displayName = displayName;
        this.fraction = fraction;
        this.dataSupplier = dataSupplier;
    }

    public HeroData getData() {
        return dataSupplier.get();
    }

    public static class HeroData {
        private final Statistics stats;
        private final List<EconomyCreature> creatures;

        public HeroData(Statistics stats, List<EconomyCreature> creatures) {
            this.stats = stats;
            this.creatures = creatures;
        }

        public Statistics getStats() {
            return stats;
        }

        public List<EconomyCreature> getCreatures() {
            return creatures;
        }
    }


}
