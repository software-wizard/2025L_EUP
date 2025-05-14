package pl.psi.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.psi.Hero;
import pl.psi.creatures.*;
import pl.psi.Point;
import pl.psi.hero.skills.AbstractSkill;
import pl.psi.creatures.Creature;
import pl.psi.creatures.EconomyCreature;
import pl.psi.gui.MainBattleController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.hero.Artifact;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.Statistics;

public class EcoBattleConverter {

    public static void startBattle(final EconomyHero aPlayer1, final EconomyHero aPlayer2) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EcoBattleConverter.class.getClassLoader().getResource("fxml/main-battle.fxml"));
            loader.setController(new MainBattleController(convert(aPlayer1), convert(aPlayer2)));
            Scene scene = new Scene(loader.load());
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
        aPlayer1.getCreatures()
                .forEach(ecoCreature -> creatures.add(convertCreatureWithEffects(ecoCreature, aPlayer1)//zmienione tutaj
                        )
                );
        return new Hero(creatures);
        //  TODO

    }
    public static void startBankBattle(final EconomyHero aPlayer1, final Map<Point, EconomyCreature> bankEnemy) {
        Map<Point, Creature> bankEnemy1 = convertEnemies(bankEnemy);

        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EcoBattleConverter.class.getClassLoader().getResource("fxml/main-battle.fxml"));
            loader.setController(new MainBattleController(convert(aPlayer1), bankEnemy1));
            Scene scene = new Scene(loader.load());
            final Stage aStage = new Stage();
            aStage.setScene(scene);
            aStage.setX(5);
            aStage.setY(5);
            aStage.show();
        } catch (final IOException aE) {
            aE.printStackTrace();
        }
    }

    public static Map<Point, Creature> convertEnemies(Map<Point, EconomyCreature> economyMap) {
        NecropolisFactory factory = new NecropolisFactory();
        Map<Point, Creature> result = new HashMap<>();

        for (Map.Entry<Point, EconomyCreature> entry : economyMap.entrySet()) {
            EconomyCreature ecoCreature = entry.getValue();
            Creature creature = factory.create(
                    ecoCreature.isUpgraded(),
                    ecoCreature.getTier(),
                    ecoCreature.getAmount(),
                    ecoCreature.getReduceDamageFactor()
            );
            result.put(entry.getKey(), creature);
        }

        return result;
    }
// Poprawiona metoda convertCreatureWithEffects
    public static Creature convertCreatureWithEffects(EconomyCreature ecoCreature, EconomyHero ecoHero) {

        CreatureStatistic baseStats = ecoCreature.getStats(); // Pobieramy statystyki z klasy EconomyCreature
        StatsModifier totalBonus = new StatsModifier(ecoHero.getTotalStatistics().getAttack(), ecoHero.getTotalStatistics().getDefense()); //początkowe bonusy

        CreatureStatisticIf modifiedStats = new ModifiedCreatureStats(baseStats, totalBonus);

        return new Creature.Builder()
                .statistic(modifiedStats)
                .amount(ecoCreature.getAmount())
                .build();
    }
}
