package pl.psi.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.hero.EconomyHero;

import java.io.IOException;

public class WindowManager {

    public static void openShop(EconomyHero hero) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowManager.class.getClassLoader()
                    .getResource("fxml/eco.fxml"));
            loader.setController(new EcoController(hero));

            final Scene scene = new Scene(loader.load());
            Stage aStage = new Stage();
            aStage.setScene(scene);
            aStage.setX(5);
            aStage.setY(5);
            aStage.show();
        } catch (final IOException aE) {
            aE.printStackTrace();
        }
    }
}
