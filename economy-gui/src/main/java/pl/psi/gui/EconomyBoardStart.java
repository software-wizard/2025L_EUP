package pl.psi.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.hero.EconomyHero;
import pl.psi.map.resources.Resources;

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
        loader.setController( new EconomyBoardController( hero1(), hero2() ));
        final Scene scene = new Scene( loader.load() );
        aStage.setScene( scene );
        aStage.setX( 5 );
        aStage.setY( 5 );
        aStage.show();
    }

    private EconomyHero hero1()
    {
        final EconomyHero hero1 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, new Resources(3000,0,0,0,0,0,0));
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero1.addCreature( factory.create( false, 1, 1 ));
        hero1.addCreature( factory.create( false, 1, 1 ));
        return hero1;
    }

    private EconomyHero hero2()
    {
        final EconomyHero hero2 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, new Resources(4000,0,0,0,0,0,0));
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero2.addCreature( factory.create( false, 2, 1 ));
        return hero2;
    }
}
