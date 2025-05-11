package pl.psi.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.gui.Upgrades.UpgradeController;
import pl.psi.gui.shops.*;
import pl.psi.hero.EconomyHero;
import pl.psi.map.MapObjectIf;
import pl.psi.map.buildings.Castle;

import java.io.IOException;

public class WindowManager {

    public static void openShop(EconomyHero hero, Castle castle) {
        try {
            FXMLLoader loader = new FXMLLoader(WindowManager.class.getResource("/fxml/shop-category.fxml"));
            Parent root = loader.load();

            ShopCategoryController controller = loader.getController();
            controller.init(hero, castle);  // Przekazujemy dane po załadowaniu FXML

            Stage stage = new Stage();
            stage.setTitle("Choose Shop");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void openUpgrades(EconomyHero hero, Castle castle) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowManager.class.getClassLoader()
                    .getResource("fxml/upgrades.fxml"));
            loader.setController(new UpgradeController(hero, castle));

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

    public static void openCreatureShop(EconomyHero hero, Castle castle) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowManager.class.getClassLoader()
                    .getResource("fxml/creature-shop.fxml"));
            loader.setController(new CreatureShopController(hero, castle));

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

    public static void openSpellShop(EconomyHero hero, Castle castle) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowManager.class.getClassLoader()
                    .getResource("fxml/spell-shop.fxml"));
            loader.setController(new SpellShopController(hero, castle));

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
    public static void openArtifactShop(EconomyHero hero, Castle castle) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowManager.class.getClassLoader()
                    .getResource("fxml/artifact-shop.fxml"));
            loader.setController(new ArtifactShopController(hero, castle));

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
    public static void openSkillShop(EconomyHero hero, Castle castle) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowManager.class.getClassLoader()
                    .getResource("fxml/skill-shop.fxml"));
            loader.setController(new SkillShopController(hero, castle));

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
