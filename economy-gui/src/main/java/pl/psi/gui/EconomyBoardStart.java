package pl.psi.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.Point;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.gui.startchoice.HeroSelection;
import pl.psi.hero.artifacts.Artifact;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.Statistics;
import pl.psi.hero.artifacts.ArtifactType;
import pl.psi.map.MapObjectIf;
import pl.psi.map.buildings.Castle;
import pl.psi.map.buildings.bank.Bank;
import pl.psi.map.buildings.bank.BankStatistics;
import pl.psi.map.resources.Gold;
import pl.psi.map.resources.Resources;
import pl.psi.map.resources.generators.*;

import java.io.IOException;
import java.util.Map;

public class EconomyBoardStart extends Application {

    public static void main( final String[] args )
    {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        HeroSelection.showAndWait().thenAccept(heroes -> {
            EconomyHero hero1 = heroes[0];
            EconomyHero hero2 = heroes[1];

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/economy-board.fxml"));
                loader.setController(new EconomyBoardController(hero1, hero2, map()));
                Scene scene = new Scene(loader.load());

                primaryStage.setScene(scene);
                primaryStage.setTitle("Mapa Ekonomiczna");
                primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Map<Point, MapObjectIf> map() {
        return Map.ofEntries(
                Map.entry(new Point(4,4), new Artifact(ArtifactType.SWORD_OF_HELLFIRE)),
                Map.entry(new Point(17,1), new Castle()),
                Map.entry(new Point(1,7), new Castle()),
                Map.entry(new Point(3,2), new ResourceGenerator(ResourceGenType.GEM)),
                Map.entry(new Point(5,6), new ResourceGenerator(ResourceGenType.GOLD)),
                Map.entry(new Point(8,1), new ResourceGenerator(ResourceGenType.MERCURY)),
                Map.entry(new Point(10,4), new ResourceGenerator(ResourceGenType.WOOD)),
                Map.entry(new Point(13,7), new ResourceGenerator(ResourceGenType.SULFUR)),
                Map.entry(new Point(15,2), new ResourceGenerator(ResourceGenType.CRYSTAL)),
                Map.entry(new Point(6,8), new ResourceGenerator(ResourceGenType.ORE)),
                Map.entry(new Point(9,3), new Gold(new Resources(1000,0,0,0,0,0,0))),
                Map.entry(new Point(10,6), new Gold(new Resources(1000,0,0,0,0,0,0))),
                Map.entry(new Point(2,2), new Bank(BankStatistics.CASTLE_1)),
                Map.entry(new Point(8,8), new Bank(BankStatistics.CASTLE_2))
        );
    }

}
