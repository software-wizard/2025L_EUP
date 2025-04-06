package pl.psi;

import pl.psi.creatures.Creature;
import org.junit.jupiter.api.Test;
import pl.psi.FirstAidTent;
public class FirstAidTentTest {



    @Test

        // Testuje leczenie rannej jednostki za pomocą FirstAidTent. Sprawdzamy, czy jednostka otrzymuje 20 punktów zdrowia,
        // ale nie przekracza swojego maksymalnego HP.

    void healCreatureTest() {

        Creature attacker = new Creature.Builder()
                .statistic(new TestStats(10)) // słaby atak
                .amount(1)
                .build();

        Creature injured = new Creature.Builder()
                .statistic(new TestStats(100)) // mocna obrona i HP
                .amount(1)
                .build();

        // Atak, żeby zredukować currentHp (zamiast sztucznie ustawiać)
        attacker.attack(injured);

        int hpAfterAttack = injured.getCurrentHp(); // np. 80
        assertThat(hpAfterAttack).isLessThan(100);

        FirstAidTent tent = new FirstAidTent();
        tent.healCreature(injured);

        assertThat(injured.getCurrentHp())
                .isEqualTo(Math.min(hpAfterAttack + 20, injured.getMaxHp()));
    }


    @Test

        // Testuje, że zdrowa jednostka nie zostanie uleczona. Sprawdzamy, czy jednostka, która ma pełne HP,
        // nie jest leczona przez FirstAidTent.

    void healsNotHealthy() {
        // Tworzymy zdrową jednostkę z użyciem Buildera
        Creature healthy = new Creature.Builder()
                .statistic(new TestStats(100)) // Ustawiamy statystyki (maxHp = 100)
                .amount(1) // Jedna jednostka
                .build(); // Budujemy obiekt

        // Tworzymy FirstAidTent
        FirstAidTent tent = new FirstAidTent();

        // Leczymy jednostkę
        tent.healCreature(healthy);

        // Sprawdzamy, czy HP pozostało na 100, ponieważ jednostka nie powinna być leczona
        assertEquals(100, healthy.getCurrentHp(), "Zdrowa jednostka nie powinna być leczona");
    }


    @Test
// Testuje leczenie jednej jednostki w armii. Tworzymy dwie ranne jednostki, które są atakowane,
// a następnie FirstAidTent leczy tylko jedną z nich.
    void testHealOneUnitInArmy() {
        // Tworzymy dwie ranne jednostki z użyciem Buildera
        Creature attacker = new Creature.Builder()
                .statistic(new TestStats(50)) // słaby atak
                .amount(1)
                .build();

        Creature injured1 = new Creature.Builder()
                .statistic(new TestStats(100)) // mocna obrona i HP
                .amount(1)
                .build();
        Creature injured2 = new Creature.Builder()
                .statistic(new TestStats(100)) // Ustawiamy statystyki (maxHp = 100)
                .amount(1) // Jedna jednostka
                .build();

        // Atak, żeby zredukować currentHp (zamiast sztucznie ustawiać)
        attacker.attack(injured1);
        attacker.attack(injured2);

        // Tworzymy FirstAidTent
        FirstAidTent tent = new FirstAidTent();

        // Leczymy armię
        tent.healArmy(List.of(injured1, injured2));

        // Sprawdzamy, czy jedna z jednostek została uleczona do 70 HP, a druga nie została zmieniona
        if (injured1.getCurrentHp() == 70) {
            assertEquals(70, injured1.getCurrentHp(), "Injured unit 1 should be healed to 70 HP.");
            assertEquals(100, injured2.getCurrentHp(), "Injured unit 2 should not be healed.");
        } else if (injured2.getCurrentHp() == 70) {
            assertEquals(100, injured1.getCurrentHp(), "Injured unit 1 should not be healed.");
            assertEquals(70, injured2.getCurrentHp(), "Injured unit 2 should be healed to 70 HP.");
        } else {
            fail("One of the injured units should be healed to 70 HP.");
        }
    }


    @Test

        // Testuje, że zdrowa armia nie jest leczona przez FirstAidTent. Sprawdzamy, czy zdrowe jednostki pozostają
        // w pełni zdrowe, jeśli nie zostały ranne.

    void testDoesNotHealWhenArmyIsHealthy() {
        // Tworzymy dwie zdrowe jednostki z użyciem Buildera
        Creature healthy1 = new Creature.Builder()
                .statistic(new TestStats(100)) // Ustawiamy statystyki (maxHp = 100)
                .amount(1) // Jedna jednostka
                .build();
        Creature healthy2 = new Creature.Builder()
                .statistic(new TestStats(100)) // Ustawiamy statystyki (maxHp = 100)
                .amount(1) // Jedna jednostka
                .build();

        // Tworzymy FirstAidTent
        FirstAidTent tent = new FirstAidTent();

        // Próba leczenia zdrowej armii
        tent.healArmy(List.of(healthy1, healthy2));

        // Sprawdzamy, czy jednostki pozostały w pełni zdrowe (100 HP)
        assertEquals(100, healthy1.getCurrentHp(), "Healthy unit should not be healed.");
        assertEquals(100, healthy2.getCurrentHp(), "Healthy unit should not be healed.");
    }

    // Testuje, czy jednostka nie przekroczy swojego maksymalnego HP po leczeniu
    @Test
    // Sprawdzamy, że jednostka nie przekroczy swojego maksymalnego HP podczas leczenia.
    void testDoesNotExceedMaxHp() {
        Creature injured = new Creature.Builder()
                .statistic(new TestStats(100)) // Ustawiamy statystyki (maxHp = 100)
                .amount(1) // Jedna jednostka
                .build();

        // Ustawiamy, że HP jest już 90 (po ataku)
        injured.setCurrentHp(90);

        FirstAidTent tent = new FirstAidTent();

        // Leczymy jednostkę
        tent.healCreature(injured);

        // Sprawdzamy, że HP nie przekroczyło maksymalnego (100)
        assertEquals(100, injured.getCurrentHp(), "Unit's HP should not exceed max HP.");
    }

    // Testuje, że leczenie nie odbywa się, jeśli jednostka nie ma wystarczającej ilości punktów życia
    @Test
    // Testuje, czy FirstAidTent nie leczy, gdy jednostka nie ma żadnych obrażeń.
    void testDoesNotHealIfAlreadyFullHealth() {
        Creature healthy = new Creature.Builder()
                .statistic(new TestStats(100)) // Ustawiamy statystyki (maxHp = 100)
                .amount(1) // Jedna jednostka
                .build();

        // Tworzymy FirstAidTent
        FirstAidTent tent = new FirstAidTent();

        // Leczymy jednostkę
        tent.healCreature(healthy);

        // Sprawdzamy, czy HP pozostało na 100, ponieważ jednostka jest zdrowa
        assertEquals(100, healthy.getCurrentHp(), "Healthy unit should not be healed.");
    }
}