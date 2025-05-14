package pl.psi.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.psi.Hero;
import pl.psi.creatures.*;
import pl.psi.gui.MainBattleController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.hero.Artifact;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.Statistics;

public class EcoBattleConverter {

    public static void startBattle(final EconomyHero aPlayer1, final EconomyHero aPlayer2) {
        Scene scene = null;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EcoBattleConverter.class.getClassLoader()
                    .getResource("fxml/main-battle.fxml"));
            loader.setController(new MainBattleController(convert(aPlayer1), convert(aPlayer2)));
            scene = new Scene(loader.load());
            final Stage aStage = new Stage();
            aStage.setScene(scene);
            aStage.setX(5);
            aStage.setY(5);
            aStage.show();
        } catch (final IOException aE) {
            aE.printStackTrace();
        }
    }

    public static Hero convert(final EconomyHero aPlayer1) {
        final List<Creature> creatures = new ArrayList<>();
        final NecropolisFactory factory = new NecropolisFactory();
        aPlayer1.getCreatures()
                .forEach(ecoCreature -> creatures.add(
                                convertCreatureWithEffects(ecoCreature, aPlayer1)//zmienione tutaj
                        )
                );
        return new Hero(creatures);
        //  TODO

    }
// Poprawiona metoda convertCreatureWithEffects
    public static Creature convertCreatureWithEffects(EconomyCreature ecoCreature, EconomyHero ecoHero) {
        // Używamy statystyk stworzenia (ecoCreature) zamiast bohatera (ecoHero)
        // 1. Pobierz podstawowe statystyki stworzenia z `ecoCreature`
        CreatureStatistic baseStats = ecoCreature.getStats(); // Pobieramy statystyki z klasy EconomyCreature

        // Tworzymy nową instancję Statistics dla bonusów z artefaktów
        //Statistics totalBonusStats = new Statistics(0, 0, 0, 0); // Inicjalizujemy statystyki bonusów


        // Tworzymy StatsModifier na podstawie bonusów z artefaktów
        // 2. Tworzymy zmienne do przechowywania bonusów z artefaktów
        StatsModifier totalBonus = new StatsModifier(0, 0); //początkowe bonusy

        // 3. Iterujemy przez artefakty w `ecoHero` i dodajemy ich bonusy do `totalBonus`
        for (Artifact artifact : ecoHero.getArtifacts()) {
            // Dodajemy efekty artefaktów

            //totalBonusStats.increase(artifact.getBonuses()); // Za pomocą metody increase dodajemy bonusy z artefaktów
            totalBonus = new StatsModifier(
                    totalBonus.getAttackBonus() + artifact.getBonuses().getAttack(),
                    totalBonus.getArmorBonus() + artifact.getBonuses().getDefense()
            );
        }

        // Teraz mamy zaktualizowane statystyki (ataku, obrony itp.) w `totalBonusStats`
        // 4. Zmodyfikuj statystyki stworzenia, uwzględniając bonusy z artefaktów
        CreatureStatisticIf modifiedStats = new ModifiedCreatureStats(baseStats, totalBonus);

        // Tworzymy obiekt DamageCalculatorIf (zakładając, że jest domyślny)
//    DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());

        // Zwracamy stworzenie z modyfikowanymi statystykami i kalkulatorem obrażeń
        // 5. Zwróć nowo utworzone stworzenie (`Creature`), uwzględniając bonusy
        return new Creature.Builder()
                .statistic(modifiedStats)
                .amount(ecoCreature.getAmount())
                .build();
    }
}
