package pl.psi;

import com.google.common.collect.Range;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Start extends Application {

    public Start() {
    }

    static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        Scene scene = null;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start.class.getClassLoader().getResource("fxml/main-battle.fxml"));
            loader.setController(new MainBattleController(createP1(), createP2()));
            scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setX(5);
            primaryStage.setY(5);
            primaryStage.show();
        } catch (final IOException aE) {
            aE.printStackTrace();
        }
    }

    private List<Creature> createP2() {
        return List.of(new Creature(10, 1, 5, 5, Range.closed(5, 5), 2));
    }

    private List<Creature> createP1() {
        return List.of(new Creature(10, 1, 5, 5, Range.closed(5, 5), 5));
    }

}
