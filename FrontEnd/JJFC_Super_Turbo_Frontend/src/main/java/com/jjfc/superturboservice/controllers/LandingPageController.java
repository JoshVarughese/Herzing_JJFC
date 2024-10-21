package com.jjfc.superturboservice.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LandingPageController {

    @FXML
    private Button tableServiceButton;

    @FXML
    private Button foodManagementButton;

    @FXML
    private Button frontServiceButton;

    @FXML
    private void initialize() {
        tableServiceButton.setOnAction(e -> openTableService());
        foodManagementButton.setOnAction(e -> openFoodManagement());
        frontServiceButton.setOnAction(e -> openFrontService());
    }

    private void openTableService() {
        loadNewScene("/com/jjfc/superturboservice/jjfc_super_turbo_frontend/table_service.fxml", "Table Service");
    }

    private void openFoodManagement() {
        loadNewScene("/com/jjfc/superturboservice/jjfc_super_turbo_frontend/inventory_service.fxml", "Food Management (Inventory Management)");
    }

    private void openFrontService() {
        loadNewScene("/com/jjfc/superturboservice/jjfc_super_turbo_frontend/front_service.fxml", "Front Service");
    }

    private void loadNewScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) tableServiceButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
