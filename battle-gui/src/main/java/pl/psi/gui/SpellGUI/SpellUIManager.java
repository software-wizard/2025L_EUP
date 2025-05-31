package pl.psi.gui.SpellGUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.psi.BattlePoint;
import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Spells.Spell;
import pl.psi.creatures.Creature;

import java.io.IOException;

public class SpellUIManager {

    private final GameEngine gameEngine;
    private final SpellCastingManager spellManager;
    private final Runnable guiRefresher;

    public SpellUIManager(GameEngine gameEngine, SpellCastingManager spellManager, Runnable guiRefresher) {
        this.gameEngine = gameEngine;
        this.spellManager = spellManager;
        this.guiRefresher = guiRefresher;
    }

    public void openSpellDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/spell-dialog.fxml"));
            VBox dialogRoot = loader.load();
            SpellDialogController controller = loader.getController();
            Hero currentHero = gameEngine.getCurrentHero();

            controller.setSpells(currentHero.getSpells(), spell -> {
                spellManager.activate(spell);
                guiRefresher.run();
            });

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(dialogRoot));
            dialogStage.setTitle("Spells");
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void confirmSpellCast(Creature creature, BattlePoint targetPoint) {
        Spell selectedSpell = spellManager.getSelectedSpell();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie zaklęcia");
        alert.setHeaderText("Rzucić zaklęcie " + selectedSpell.getName() + " na " + creature + "?");

        ButtonType okButton = new ButtonType("Tak", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);

        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                gameEngine.castSpell(selectedSpell, creature);
            }
            spellManager.deactivate();
            guiRefresher.run();
        });
    }

    public void showSpellCastDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Zaklęcie rzucone");
        alert.setHeaderText(null);
        alert.setContentText("Zaklęcie zostało pomyślnie rzucone!");
        alert.showAndWait();
    }
}
