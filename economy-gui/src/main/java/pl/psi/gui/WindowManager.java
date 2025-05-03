package pl.psi.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.gui.Upgrades.UpgradeController;
import pl.psi.hero.EconomyHero;
import pl.psi.map.MapObjectIf;
import pl.psi.map.buildings.Castle;

import java.io.IOException;

public class WindowManager {

    public static void openShop(EconomyHero hero, Castle castle) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowManager.class.getClassLoader()
                    .getResource("fxml/eco.fxml"));
            loader.setController(new EcoController(hero, castle)); // pass castle here

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


    public static void openUpgrades(EconomyHero hero, MapObjectIf castle) {
        try {
            FXMLLoader loader = new FXMLLoader(WindowManager.class.getResource("/fxml/upgrades.fxml"));
            Parent root = loader.load();
            UpgradeController controller = loader.getController();
            controller.setCastle((Castle) castle);
            controller.setHero(hero);
            Stage stage = new Stage();
            stage.setTitle("Castle Upgrades");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
