package pl.psi.gui;

import com.google.common.collect.BiMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.BattlePoint;
import pl.psi.SpecialField;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Optional;

public class MainBattleController implements PropertyChangeListener {
    private final GameEngine gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;

//    public MainBattleController( final Hero aHero1, final Hero aHero2 )
//    {
//        gameEngine = new GameEngine( aHero1, aHero2 );
//    }

    public MainBattleController(final Hero aHero1, final Hero aHero2, final Map<BattlePoint, Creature> bankEnemy, BiMap<BattlePoint, SpecialField> aSpecialField) {
        gameEngine = new GameEngine(aHero1, aHero2, aSpecialField, bankEnemy);
    }

    @FXML
    private void initialize() {
        refreshGui();
        gameEngine.addObserver(this);
        passButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> gameEngine.pass());
    }

    private void refreshGui() {
        gridMap.getChildren()
                .clear();
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 10; y++) {
                BattlePoint currentBattlePoint = new BattlePoint(x, y);
                Optional<Creature> creature = gameEngine.getCreature(currentBattlePoint);
                final MapTile mapTile = new MapTile("");
                creature.ifPresent(c -> mapTile.setName(c.toString()));
                if (gameEngine.isCurrentCreature(currentBattlePoint)) {
                    mapTile.setBackground(Color.GREENYELLOW);
                }
                if (gameEngine.canMove(currentBattlePoint)) {
                    mapTile.setBackground(Color.GREY);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            (e) -> {
                                gameEngine.move(currentBattlePoint);
                            });
                }
                if (gameEngine.canAttack(currentBattlePoint)) {
                    mapTile.setBackground(Color.RED);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            (e) -> {
                                gameEngine.attack(currentBattlePoint);
                            });
                }
                SpecialField specialField = gameEngine.getSpecialFields().get(currentBattlePoint);
                if (specialField != null) {
                    mapTile.setBackground(getColor(specialField));
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        gameEngine.interact(currentBattlePoint);
                    });
                }
                gridMap.add(mapTile, x, y);
            }
        }
    }

    private Color getColor(SpecialField specialField) {
        if (specialField.getColor() == SpecialField.Color.BROWN) {
            return Color.BROWN;
        } else if (specialField.getColor() == SpecialField.Color.BLUE) {
            return Color.BLUE;
        }
        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
    }
}
