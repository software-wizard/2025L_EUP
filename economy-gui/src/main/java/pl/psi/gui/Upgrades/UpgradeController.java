package pl.psi.gui.Upgrades;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import lombok.Getter;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.town.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpgradeController {

    @FXML
    private ListView<String> upgradeList;

    private Town town;
    private EconomyHero hero;

    public void setData(EconomyHero hero, Town town) {
        this.hero = hero;
        this.town = town;
        if (upgradeList != null) {
            refreshUpgrades();
        }
    }

    @FXML
    public void initialize() {
        refreshUpgrades();
    }

    private void refreshUpgrades() {
        if (town == null || hero == null || upgradeList == null) return;

        upgradeList.getItems().clear();

        Stream.concat(
                        Arrays.stream(TownBuilding.values()),
                        Arrays.stream(UpgradeBuildings.values())
                ).filter(b -> !town.hasBuilt(b))
                .filter(b -> b.getPrerequisites().stream().allMatch(town::hasBuilt))
                .map(BuildingDisplay::new)
                .map(BuildingDisplay::toString)
                .forEach(upgradeList.getItems()::add);
    }


    @FXML
    private void handleBuy() {
        String selectedItem = upgradeList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;

        try {
            String buildingName = selectedItem.split("] ")[1].split(" ")[0];

            BuildingType selected;
            if (isTownBuilding(buildingName)) {
                selected = TownBuilding.valueOf(buildingName);
            } else {
                selected = UpgradeBuildings.valueOf(buildingName);
            }

            town.build(selected, hero);
            showAlert(Alert.AlertType.INFORMATION, "Upgrade Purchased", selected + " unlocked!");
            refreshUpgrades();

        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Selected upgrade is invalid.");
        } catch (IllegalStateException e) {
            showAlert(Alert.AlertType.ERROR, "Cannot Purchase", e.getMessage());
        }
    }

    private boolean isTownBuilding(String name) {
        try {
            TownBuilding.valueOf(name);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private static class BuildingDisplay {
        @Getter
        private final BuildingType building;
        private final String displayName;

        BuildingDisplay(BuildingType building) {
            this.building = building;

            String category;
            if (building instanceof TownBuilding) {
                category = ((TownBuilding) building).getCategory().name();
            } else {
                category = "CREATURE_UPGRADE";
            }

            this.displayName = String.format("[%s] %s - Cost: %s",
                    category,
                    building.toString(),
                    building.getCost());
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

}
