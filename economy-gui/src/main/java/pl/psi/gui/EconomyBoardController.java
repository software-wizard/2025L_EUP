package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.psi.Point;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.hero.EconomyHero;
import pl.psi.map.BoardEconomyEngine;
import pl.psi.map.InteractableIf;
import pl.psi.map.buildings.BuildingIf;
import pl.psi.map.resources.Resources;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Optional;

public class EconomyBoardController implements PropertyChangeListener {
    private final BoardEconomyEngine gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;

    @FXML private Label goldLabel;
    @FXML private Label woodLabel;
    @FXML private Label oreLabel;
    @FXML private Label mercuryLabel;
    @FXML private Label sulphurLabel;
    @FXML private Label crystalLabel;
    @FXML private Label gemsLabel;

    private EconomyHero battleHero1;
    private EconomyHero battleHero2;

    public EconomyBoardController(final EconomyHero hero1, final EconomyHero hero2) {
        gameEngine = new BoardEconomyEngine(hero1, hero2);
        battleHero1 = hero1;
        battleHero2 = hero2;
    }

    @FXML
    private void initialize() {
        refreshGui();
        updateResourceDisplay();
        gameEngine.addObserver(this);
        passButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> gameEngine.pass());
    }


    private void refreshGui() {
        gridMap.getChildren().clear();

        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                Point currentPoint = new Point(x, y);

                Optional<InteractableIf> interactionObj = gameEngine.getInteractable(currentPoint);
                Optional<BuildingIf> building = gameEngine.getBuilding(currentPoint);
                Optional<EconomyHero> hero = gameEngine.getHero(currentPoint);

                final EconomyTile mapTile = new EconomyTile("");
                hero.ifPresent(c -> mapTile.setName("hero1"));

                handleTileActions(currentPoint, mapTile, interactionObj, building);
                gridMap.add(mapTile, x, y);
            }
        }
        updateResourceDisplay();
    }

    private void handleTileActions(Point currentPoint, EconomyTile mapTile, Optional<InteractableIf> interactionObj, Optional<BuildingIf> buildingObj) {

        if (gameEngine.isCurrentHero(currentPoint)) {
            mapTile.setBackground(Color.GREENYELLOW);
        }

        //movement action
        if (gameEngine.canMove(currentPoint)) {
            mapTile.setBackground(Color.GREY);
            mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> gameEngine.move(currentPoint));
        }

        //interaction action
        if (gameEngine.canInteract(currentPoint)) {
            interactionObj.ifPresent(interactableIf -> mapTile.setImage(interactableIf.getPath()));
            mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> gameEngine.move(currentPoint));
        }

        //attack action
        if (gameEngine.canAttack(currentPoint)) {
            mapTile.setBackground(Color.RED);
            mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> EcoBattleConverter.startBattle(battleHero1, battleHero2));
        }

        if(gameEngine.canEnterCastle(currentPoint)) {
            buildingObj.ifPresent(buildingIf -> mapTile.setImage(buildingIf.getPath()));
            mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> openShop(gameEngine.getCurrentHero()));
        }
    }

    private void openShop(EconomyHero hero) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader()
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


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
    }
}
