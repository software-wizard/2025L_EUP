package pl.psi.gui.startchoice;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.psi.hero.EconomyHero;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class HeroSelection{

    public static CompletableFuture<EconomyHero[]> showAndWait() {
        CompletableFuture<EconomyHero[]> resultFuture = new CompletableFuture<>();

        try {
            FXMLLoader loader = new FXMLLoader(HeroSelection.class.getResource("/fxml/hero-selection.fxml"));
            Scene scene = new Scene(loader.load());

            HeroSelectionController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Wybór Bohaterów");
            dialogStage.setScene(scene);

            controller.setDialogStage(dialogStage);
            controller.setOnHeroesSelected((hero1, hero2) -> {
                resultFuture.complete(new EconomyHero[]{hero1, hero2});
                dialogStage.close();
            });

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            resultFuture.completeExceptionally(e);
        }

        return resultFuture;
    }
}

