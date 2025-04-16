package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.hero.EconomyHero;
import pl.psi.map.BoardEconomyEngine;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class EconomyBoardController implements PropertyChangeListener {
    private final BoardEconomyEngine gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;

    public EconomyBoardController(final EconomyHero hero1, final EconomyHero hero2) {
        gameEngine = new BoardEconomyEngine(hero1, hero2);
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
                Point currentPoint = new Point(x, y);
                Optional<EconomyHero> hero = gameEngine.getHero(currentPoint);
                final EconomyTile mapTile = new EconomyTile("");
                hero.ifPresent(c -> mapTile.setName("hero1"));
                //TODO create methods to get the name of each hero
                if (gameEngine.isCurrentHero(currentPoint)) {
                    mapTile.setBackground(Color.GREENYELLOW);
                }
                if (gameEngine.canMove(currentPoint)) {
                    mapTile.setBackground(Color.GREY);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            (e) -> {
                                gameEngine.move(currentPoint);
                            });
                }
                if (gameEngine.isInteractable(currentPoint)) {
                    mapTile.setImage("/objects/goldPile1.png");
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            (e) -> {
                                gameEngine.move(currentPoint);
                            });
                    ;
                }
//                if( gameEngine.canAttack( currentPoint ) )
//                {
//                    mapTile.setBackground( Color.RED );
//                    mapTile.addEventHandler( MouseEvent.MOUSE_CLICKED,
//                            ( e ) -> { gameEngine.attack( currentPoint ); } );
//                }
                gridMap.add(mapTile, x, y);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
    }
}
