package pl.psi.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.Point;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.Statistics;
import pl.psi.map.MapObjectIf;
import pl.psi.map.buildings.Castle;
import pl.psi.map.buildings.bank.Bank;
import pl.psi.map.buildings.bank.BankStatistics;
import pl.psi.map.resources.Gold;
import pl.psi.map.resources.Resources;
import pl.psi.map.resources.generators.GoldGenerator;
import pl.psi.map.resources.generators.MercuryGenerator;
import pl.psi.map.resources.generators.WoodGenerator;

import java.util.Map;

public class EconomyBoardStart extends Application {

    public static void main( final String[] args )
    {
        launch();
    }

    @Override
    public void start( final Stage aStage ) throws Exception
    {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getClassLoader()
                .getResource( "fxml/economy-board.fxml" ) );
        loader.setController( new EconomyBoardController( hero1(), hero2(), map() ));
        final Scene scene = new Scene( loader.load() );
        aStage.setScene( scene );
        aStage.setX( 5 );
        aStage.setY( 5 );
        aStage.show();
    }

    private EconomyHero hero1()
    {
        Statistics aStats = new Statistics(10, 10, 10, 10);
        final EconomyHero hero1 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, new Resources(3000,50,50,50,50,50,50), aStats);
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero1.addCreature( factory.create( false, 1, 1 ));
        hero1.addCreature( factory.create( false, 1, 1 ));
        return hero1;
    }

    private EconomyHero hero2()
    {
        Statistics aStats = new Statistics(10, 10, 10, 10);
        final EconomyHero hero2 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, new Resources(4000,0,0,0,0,0,0),aStats);
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero2.addCreature( factory.create( false, 2, 1 ));
        return hero2;
    }

    private Map<Point, MapObjectIf> map() {
        return Map.ofEntries(
                Map.entry(new Point(17,1), new Castle()),
                Map.entry(new Point(1,7), new Castle()),
                Map.entry(new Point(3,2), new MercuryGenerator()),
                Map.entry(new Point(5,6), new MercuryGenerator()),
                Map.entry(new Point(8,1), new MercuryGenerator()),
                Map.entry(new Point(10,4), new MercuryGenerator()),
                Map.entry(new Point(13,7), new MercuryGenerator()),
                Map.entry(new Point(15,2), new MercuryGenerator()),
                Map.entry(new Point(6,8), new MercuryGenerator()),
                Map.entry(new Point(9,3), new Gold(new Resources(1000,0,0,0,0,0,0))),
                Map.entry(new Point(10,6), new Gold(new Resources(1000,0,0,0,0,0,0))),
                Map.entry(new Point(2,2), new Bank(BankStatistics.CASTLE_1)),
                Map.entry(new Point(8,8), new Bank(BankStatistics.CASTLE_2))
        );
    }

}
