package pl.psi;

import com.google.common.collect.BiMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class MainBattleController {
    private final GameEngine gameEngine;

    @FXML
    private GridPane gridMap;

    @FXML
    private Button passButton;

    @FXML
    private Button spellButton;


    public MainBattleController(final List<Creature> aHero1, final List<Creature> aHero2) {
        this.gameEngine = new GameEngine(aHero1, aHero2);
    }

    @FXML
    private void initialize() {
        refreshGui();
    }

    private void refreshGui() {
        gridMap.getChildren()
                .clear();
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 10; y++) {
                Rectangle tile = new Rectangle(60, 60, Color.WHITE);
                tile.setStroke(Color.RED);
                gridMap.add(tile, x, y);
            }
        }
    }
}
