package com.jjfc.superturboservice.controllers;

import com.jjfc.superturboservice.models.Inventory;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class InventoryServiceController {
    @FXML
    private TextField foodItemIDField;
    @FXML
    private TextField ingredientStockField;
    @FXML
    private ComboBox<String> stockAlertsComboBox;
    @FXML
    private ListView<String> inventoryList;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String BASE_URL = "http://localhost:8080/api/inventory";

    @FXML
    private void initialize() {
        stockAlertsComboBox.getItems().addAll("Low", "Medium", "High");
        loadInventory();
    }

    @FXML
    private void loadInventory() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            List<Inventory> inventories = objectMapper.readValue(response.body(), new TypeReference<List<Inventory>>() {});

            inventoryList.getItems().clear();
            for (Inventory inventory : inventories) {
                inventoryList.getItems().add("Food Item ID: " + inventory.getFoodItemID() +
                        " (Stock: " + inventory.getIngredientStock() + ", Alert: " + inventory.getStockAlerts() + ")");
            }
        } catch (Exception e) {
            showAlert("Error loading inventory", e.getMessage());
        }
    }

    @FXML
    private void addInventory() {
        String foodItemID = foodItemIDField.getText();
        String ingredientStock = ingredientStockField.getText();
        String stockAlert = stockAlertsComboBox.getValue();

        if (foodItemID.isEmpty() || ingredientStock.isEmpty() || stockAlert == null) {
            showAlert("Input Error", "Please fill in all fields.");
            return;
        }

        try {
            Inventory newInventory = new Inventory(Integer.parseInt(foodItemID), Integer.parseInt(ingredientStock), stockAlert);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(newInventory);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 201) {
                loadInventory();
                showAlert("Success", "Inventory item added successfully.");
            } else {
                showAlert("Error", "Failed to add inventory item: " + response.body());
            }
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numeric values for ID and stock.");
        } catch (Exception e) {
            showAlert("Error adding inventory item", e.getMessage());
        }
    }

    @FXML
    private void updateInventory() {
        String selectedItem = inventoryList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert("Selection Error", "Please select an inventory item to update.");
            return;
        }

        Integer foodItemID = Integer.parseInt(selectedItem.split(":")[1].split("\\(")[0].trim());
        String newIngredientStock = ingredientStockField.getText();
        String newStockAlert = stockAlertsComboBox.getValue();

        if (newIngredientStock.isEmpty() || newStockAlert == null) {
            showAlert("Input Error", "Please provide a new stock quantity and alert level.");
            return;
        }

        try {
            Inventory updatedInventory = new Inventory(foodItemID, Integer.parseInt(newIngredientStock), newStockAlert);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(updatedInventory);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + foodItemID))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                loadInventory();
                showAlert("Success", "Inventory item updated successfully.");
            } else {
                showAlert("Error", "Failed to update inventory item: " + response.body());
            }
        } catch (Exception e) {
            showAlert("Error updating inventory item", e.getMessage());
        }
    }

    @FXML
    private void deleteInventory() {
        String selectedItem = inventoryList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert("Selection Error", "Please select an inventory item to delete.");
            return;
        }

        Integer foodItemID = Integer.parseInt(selectedItem.split(":")[1].split("\\(")[0].trim());

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + foodItemID))
                    .DELETE()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 204) {
                loadInventory();
                showAlert("Success", "Inventory item deleted successfully.");
            } else {
                showAlert("Error", "Failed to delete inventory item: " + response.body());
            }
        } catch (Exception e) {
            showAlert("Error deleting inventory item", e.getMessage());
        }
    }

    @FXML
    private void goBackToHome(ActionEvent event) {
        try {
            // Load the landing page
            Parent homePage = FXMLLoader.load(getClass().getResource("/com/jjfc/superturboservice/jjfc_super_turbo_frontend/landing_page.fxml"));
            Scene homeScene = new Scene(homePage);

            // Get current stage and set the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(homeScene);
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Failed to load the landing page.");
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
