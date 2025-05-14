package pl.psi.gui.Upgrades;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.Castle;
import pl.psi.creatures.UpgradeBuildings;

public class UpgradeController {

    @FXML
    private ListView<String> upgradeList;

    private Castle castle;
    private EconomyHero hero;

    public UpgradeController() {
    }

    @FXML
    public void initialize() {
        refreshUpgrades();
    }

    public void setData(EconomyHero hero, Castle castle) {
        this.hero = hero;
        this.castle = castle;

        // Can be safely called here if upgradeList is already injected
        if (upgradeList != null) {
            refreshUpgrades();
        }
    }

    private void refreshUpgrades() {
        if (castle == null || hero == null || upgradeList == null) {
            return;
        }

        upgradeList.getItems().clear();

        for (UpgradeBuildings building : UpgradeBuildings.values()) {
            boolean alreadyBuilt = castle.hasBuilt(building);
            boolean prereqMet = !building.isUpgrade() || castle.hasBuilt(building.getRequiredBuilding());

            if (!alreadyBuilt && prereqMet) {
                String entry = String.format("%s  - Cost: %s",
                        building.name(),
                        building.getCost().toString());
                upgradeList.getItems().add(entry);
            }
        }
    }

    @FXML
    private void handleBuy() {
        String selectedItem = upgradeList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        try {
            String buildingName = selectedItem.split(" ")[0];
            UpgradeBuildings selectedBuilding = UpgradeBuildings.valueOf(buildingName);

            castle.build(selectedBuilding, hero);
            showAlert(AlertType.INFORMATION, "Upgrade Purchased",
                    selectedBuilding + " unlocked!");

            refreshUpgrades();
        } catch (IllegalArgumentException e) {
            showAlert(AlertType.ERROR, "Error", "Selected upgrade is invalid.");
        } catch (IllegalStateException e) {
            showAlert(AlertType.ERROR, "Cannot Purchase", e.getMessage());
        }
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
