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
import pl.psi.gui.SpellGUI.SpellCastingManager;
import pl.psi.gui.SpellGUI.SpellUIManager;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Optional;

public class MainBattleController implements PropertyChangeListener {
    private final GameEngine gameEngine;
    private final SpellCastingManager spellManager = new SpellCastingManager();
    private SpellUIManager spellUIManager;

    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;
    @FXML
    private Button spellButton;

    public MainBattleController(final Hero aHero1, final Hero aHero2, final Map<BattlePoint, Creature> bankEnemy, BiMap<BattlePoint, SpecialField> aSpecialField) {
        gameEngine = new GameEngine(aHero1, aHero2, aSpecialField, bankEnemy);
    }

    @FXML
    private void initialize() {
        spellUIManager = new SpellUIManager(gameEngine, spellManager, this::refreshGui);

        refreshGui();
        gameEngine.addObserver(this);

        passButton.setOnMouseClicked(e -> pass());

        if (spellButton != null) {
            spellButton.setOnMouseClicked(e -> spellUIManager.openSpellDialog());
        }
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
                    mapTile.setName(getFieldName(specialField).toString());
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        gameEngine.interact(currentBattlePoint);
                    });
                }

                if (spellManager.isActive() && creature.isPresent()) {
                    mapTile.setBackground(Color.DEEPSKYBLUE);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            e -> spellUIManager.confirmSpellCast(creature.get(), currentBattlePoint));
                }

                gridMap.add(mapTile, x, y);
            }
        }
    }

    private Color getColor(SpecialField specialField) {
        if (specialField.getColor() == SpecialField.Color.BROWN) {
            return Color.BROWN;
        } else if (specialField.getColor() == SpecialField.Color.CYAN) {
            return Color.CYAN;
        } else if (specialField.getColor() == SpecialField.Color.YELLOW) {
            return Color.YELLOW;
        } else if (specialField.getColor() == SpecialField.Color.ORANGE) {
            return Color.ORANGE;
        } else if (specialField.getColor() == SpecialField.Color.GRAY) {
            return Color.GRAY;
        }
        return null;
    }

    private SpecialField.FieldName getFieldName(SpecialField specialField) {
       return specialField.getFieldName();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("SPELL_CAST".equals(evt.getPropertyName())) {
            spellUIManager.showSpellCastDialog();
        }
        refreshGui();
    }

    private void pass() {
        gameEngine.pass();
        refreshGui();
    }
}
