package pl.psi.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.psi.Hero;
import pl.psi.Point;
import pl.psi.hero.skills.AbstractSkill;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.EconomyCreature;
import pl.psi.gui.MainBattleController;
import pl.psi.creatures.NecropolisFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.hero.EconomyHero;

public class EcoBattleConverter
{

    public static void startBattle( final EconomyHero aPlayer1, final EconomyHero aPlayer2 )
    {
        Scene scene = null;
        try
        {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation( EcoBattleConverter.class.getClassLoader()
                .getResource( "fxml/main-battle.fxml" ) );
            loader.setController( new MainBattleController( convert( aPlayer1 ), convert( aPlayer2 ) ) );
            scene = new Scene( loader.load() );
            final Stage aStage = new Stage();
            aStage.setScene( scene );
            aStage.setX( 5 );
            aStage.setY( 5 );
            aStage.show();
        }
        catch( final IOException aE )
        {
            aE.printStackTrace();
        }
    }

    public static void startBankBattle(final EconomyHero aPlayer1, final Map<Point, EconomyCreature> bankEnemy)
    {
        Map<Point, Creature> bankEnemy1 = convertEnemies(bankEnemy);

        Scene scene = null;
        try
        {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation( EcoBattleConverter.class.getClassLoader()
                    .getResource( "fxml/main-battle.fxml" ) );
            loader.setController( new MainBattleController( convert( aPlayer1 ),bankEnemy1 ));
            scene = new Scene( loader.load() );
            final Stage aStage = new Stage();
            aStage.setScene( scene );
            aStage.setX( 5 );
            aStage.setY( 5 );
            aStage.show();
        }
        catch( final IOException aE )
        {
            aE.printStackTrace();
        }
    }


    public static Hero convert( final EconomyHero aPlayer1 )
    {
        final List< Creature > creatures = new ArrayList<>();
        final List<AbstractSkill> skills = aPlayer1.getSkills();
        final NecropolisFactory factory = new NecropolisFactory();
        aPlayer1.getCreatures()
            .forEach( ecoCreature -> creatures.add( factory.create( ecoCreature.isUpgraded(),
                ecoCreature.getTier(), ecoCreature.getAmount() ) ) );
        return new Hero( creatures );
    }

    public static Map<Point, Creature> convertEnemies(Map<Point, EconomyCreature> economyMap) {
        NecropolisFactory factory = new NecropolisFactory();
        Map<Point, Creature> result = new HashMap<>();
        for (Map.Entry<Point, EconomyCreature> entry : economyMap.entrySet()) {
            EconomyCreature ecoCreature = entry.getValue();
            Creature creature = factory.create(ecoCreature.isUpgraded(), ecoCreature.getTier(), ecoCreature.getAmount());
            result.put(entry.getKey(), creature);
        }
        return result;
                ecoCreature.getTier(), ecoCreature.getAmount(), ecoCreature.getReduceDamageFactor() ) ) );
        if (skills == null)
        {
            return new Hero(creatures);
        }else {
            aPlayer1.getCreatures().forEach(c->skills.forEach(s->{s.apply(c);}));
            return new Hero(creatures);
        }
    }
}
