package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.Statistics;
import pl.psi.map.BoardEconomyEngine;
import pl.psi.map.MapObjectIf;
import pl.psi.map.buildings.bank.Bank;
import pl.psi.map.buildings.town.Town;
import pl.psi.map.resources.Resources;
import pl.psi.economy.Point;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class EconomyBoardController implements PropertyChangeListener {
    private final BoardEconomyEngine gameEngine;
    @FXML private GridPane gridMap;
    @FXML private Button passButton,equipmentButton;
    @FXML private Label goldLabel, woodLabel, oreLabel, mercuryLabel, sulphurLabel, crystalLabel, gemsLabel,attackLabel,defenceLabel,powerLabel,knowledgeLabel;

    private final EconomyHero battleHero1;
    private final EconomyHero battleHero2;

    public EconomyBoardController(final EconomyHero hero1, final EconomyHero hero2, Map<Point, MapObjectIf> map) {
        this.gameEngine = new BoardEconomyEngine(hero1, hero2, map);
        this.battleHero1 = hero1;
        this.battleHero2 = hero2;
    }

    @FXML
    private void initialize() {
        refreshGui();
        updateResourceDisplay();
        gameEngine.addObserver(this);
        passButton.setOnMouseClicked(e -> gameEngine.pass());
        equipmentButton.setOnMouseClicked(e -> showEquipment());
    }

    private void refreshGui() {
        gridMap.getChildren().clear();
        for (int x = 0; x < 18; x++) {
            for (int y = 0; y < 9; y++) {
                Point point = new Point(x, y);
                EconomyTile tile = new EconomyTile("");
                renderTileContent(point, tile);
                bindTileEvents(point, tile);
                gridMap.add(tile, x, y);
            }
        }
        updateDisplay();
    }

    private void renderTileContent(Point point, EconomyTile tile) {
        if (gameEngine.isCurrentHero(point)) {
            tile.setImage("/heroes/hero1.png");
        }

        if (gameEngine.isHero(point) && !gameEngine.isCurrentHero(point)) {
            tile.setName("Other Hero");
        }

        gameEngine.getMapObject(point).ifPresent(mapObject -> {tile.setImage(mapObject.getPath());});

        if (gameEngine.canMove(point)) {
            tile.setBackground(Color.GREY);
        } else if (gameEngine.canAttack(point)) {
            tile.setBackground(Color.RED);
        } else if (gameEngine.canInteract(point)) {
            tile.setBackground(Color.YELLOW);
        }
    }

    private void bindTileEvents(Point point, EconomyTile tile) {
        if (gameEngine.canMove(point)) {
            tile.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    gameEngine.move(point);
                }
            });
        }

        if (gameEngine.canInteract(point)) {
            tile.setOnMouseClicked(e -> {
                gameEngine.move(point);
                gameEngine.interact(point);
                System.out.println("Interaction at: " + point);
                refreshGui();
            });
        }

        if (gameEngine.canAttack(point)) {
            tile.setOnMouseClicked(e -> EcoBattleConverter.startBattle(battleHero1, battleHero2));
        }

        if (gameEngine.canEnter(point)) {
            tile.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    gameEngine.move(point); // Ensure hero enters
                    gameEngine.enter(point);
                } else if (e.getButton() == MouseButton.SECONDARY) {
                    gameEngine.move(point);
                    gameEngine.secondInteraction(point);
                }
            });
        }

    }

    private void updateDisplay(){
        updateResourceDisplay();
        updateStatsDisplay();
    }

    private void updateResourceDisplay() {
        Resources res = gameEngine.getCurrentHero().getResources();
        goldLabel.setText("Gold: " + res.getGold());
        woodLabel.setText("Wood: " + res.getWood());
        oreLabel.setText("Ore: " + res.getOre());
        mercuryLabel.setText("Mercury: " + res.getMercury());
        sulphurLabel.setText("Sulphur: " + res.getSulphur());
        crystalLabel.setText("Crystal: " + res.getCrystal());
        gemsLabel.setText("Gems: " + res.getGems());
    }

    private void updateStatsDisplay() {
        Statistics stats = gameEngine.getCurrentHero().getTotalStatistics();
        attackLabel.setText("Attack: " + stats.getAttack());
        defenceLabel.setText("Defense: " + stats.getDefense());
        powerLabel.setText("Power: " + stats.getPower());
        knowledgeLabel.setText("Knowledge: " + stats.getKnowledge());
    }


    private void showEquipment() {
        WindowManager.openEquipment(gameEngine.getCurrentHero());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
        switch (evt.getPropertyName()) {
            case "OPEN_SHOP":
                Object[] data = (Object[]) evt.getNewValue();
                EconomyHero hero = (EconomyHero) data[0];
                Town optionalTown = (Town) data[1];
                WindowManager.openShop(hero, optionalTown);
                break;

            case "OPEN_UPGRADES":
                Object[] data1 = (Object[]) evt.getNewValue();
                EconomyHero hero1 = (EconomyHero) data1[0];
                Town town1 = (Town) data1[1];
                WindowManager.openUpgrades(hero1, town1);
                break;

            case "ENTER_BANK":
                Object[] data2 = (Object[]) evt.getNewValue();
                EconomyHero hero2 = (EconomyHero) data2[0];
                Bank bank = (Bank) data2[1];
                Map<Point, EconomyCreature> enemies = bank.getEnemies();
                EcoBattleConverter.startBankBattle(hero2, enemies);
                break;
        }
    }
}
