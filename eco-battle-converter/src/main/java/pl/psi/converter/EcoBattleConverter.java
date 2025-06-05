package pl.psi.converter;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.*;
import pl.psi.Spells.DamageSpell;
import pl.psi.Spells.Spell;
import pl.psi.creatures.*;
import pl.psi.economy.Point;
import pl.psi.gui.MainBattleController;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.skills.AbstractSkill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static pl.psi.hero.skills.SkillName.ARMORER;
import static pl.psi.hero.skills.SkillName.OFFENCE;

public class EcoBattleConverter {

    public static void startBattle(final EconomyHero aPlayer1, final EconomyHero aPlayer2) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            BiMap<BattlePoint, SpecialField> specialFields = HashBiMap.create();
            specialFields.put(new BattlePoint(5, 5), new DmgField());
            specialFields.put(new BattlePoint(3, 8), new SpellField());
            specialFields.put(new BattlePoint(2,4), new FieldCanOnlyBeFlown());
            loader.setLocation(EcoBattleConverter.class.getClassLoader()
                    .getResource("fxml/main-battle.fxml"));
            loader.setController(new MainBattleController(convert(aPlayer1), convert(aPlayer2), new HashMap<>(), specialFields));
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
        return new Hero(creatures, aPlayer1.getSpells().stream().map(s -> new DamageSpell(s.getName(), 1,1)).collect(Collectors.toList()));
    }

    public static void startBankBattle(final EconomyHero aPlayer1, final Map<Point, EconomyCreature> bankEnemy) {
        Map<BattlePoint, Creature> bankEnemy1 = convertEnemies(bankEnemy);

        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EcoBattleConverter.class.getClassLoader().getResource("fxml/main-battle.fxml"));
            loader.setController(new MainBattleController(convert(aPlayer1), convert(aPlayer1), bankEnemy1, HashBiMap.create()));
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

    public static Map<BattlePoint, Creature> convertEnemies(Map<Point, EconomyCreature> economyMap) {
        NecropolisFactory factory = new NecropolisFactory();
        Map<BattlePoint, Creature> result = new HashMap<>();

        for (Map.Entry<Point, EconomyCreature> entry : economyMap.entrySet()) {
            EconomyCreature ecoCreature = entry.getValue();
            Creature creature = factory.create(
                    ecoCreature.isUpgraded(),
                    ecoCreature.getTier(),
                    ecoCreature.getAmount()
            );
            result.put(new BattlePoint(entry.getKey().getX(), entry.getKey().getY()), creature);
        }

        return result;
    }

    public static Creature convertCreatureWithEffects(EconomyCreature ecoCreature, EconomyHero ecoHero) {

        CreatureStatistic baseStats = ecoCreature.getStats();
        StatsModifier totalBonus = new StatsModifier(ecoHero.getTotalStatistics().getAttack(), ecoHero.getTotalStatistics().getDefense());

        CreatureStatisticIf modifiedStats = new ModifiedCreatureStats(baseStats, totalBonus);

        if (!ecoHero.getSkills().isEmpty()) {
            float reduceDamageFactor=0;
            float bonusDamageFActor=0;
            ArrayList<AbstractSkill> skills = new ArrayList<>(ecoHero.getSkills());
            for (AbstractSkill skill : skills) {
                if (skill.getName() == ARMORER) {
                    reduceDamageFactor= skill.getFactor();
                } else if (skill.getName() == OFFENCE) {
                    bonusDamageFActor = skill.getFactor();
                }
            }
            return new Creature.Builder()
                    .statistic(modifiedStats)
                    .calculator(new ReducedDamageCalculator(reduceDamageFactor, bonusDamageFActor))
                    .amount(ecoCreature.getAmount())
                    .build();
        }

        return new Creature.Builder()
                .statistic(modifiedStats)
                .amount(ecoCreature.getAmount())
                .build();
    }
}
