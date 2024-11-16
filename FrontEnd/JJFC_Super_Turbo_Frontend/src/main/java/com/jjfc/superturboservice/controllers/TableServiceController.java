package com.jjfc.superturboservice.controllers;

import com.jjfc.superturboservice.models.Table;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.stage.Stage;

import java.util.List;

public class TableServiceController {
    @FXML
    private TextField orderStatusField;
    @FXML
    private ListView<String> tableList;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String BASE_URL = "http://localhost:8080/api/tables";

    @FXML
    private void initialize() {
        loadTables();
    }

    @FXML
    private void loadTables() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            List<Table> tables = objectMapper.readValue(response.body(), new TypeReference<List<Table>>() {});

            tableList.getItems().clear();
            for (Table table : tables) {
                tableList.getItems().add("Table ID: " + table.getTableID() + " (Status: " + table.getOrderStatus() + ")");
            }
        } catch (Exception e) {
            showAlert("Error loading tables", e.getMessage());
        }
    }

    @FXML
    private void createTable() {
        String orderStatus = orderStatusField.getText();

        if (orderStatus.isEmpty()) {
            showAlert("Input Error", "Please provide an order status.");
            return;
        }

        try {
            Table newTable = new Table(null, orderStatus);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(newTable);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


            if (response.statusCode() == 200 || response.statusCode() == 201) {
                loadTables();
                showAlert("Success", "Table added successfully.");
            } else {
                showAlert("Error", "Failed to add table: " + response.body());
            }
        } catch (IllegalArgumentException e) {
            showAlert("Input Error", "Invalid order status. Please use Available, Occupied, or Reserved.");
        } catch (Exception e) {
            showAlert("Error creating table", e.getMessage());
        }
    }


    @FXML
    private void updateTable() {
        String selectedItem = tableList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert("Selection Error", "Please select a table to update.");
            return;
        }

        Integer tableID = Integer.parseInt(selectedItem.split(":")[1].split("\\(")[0].trim());
        String newOrderStatus = orderStatusField.getText();

        if (newOrderStatus.isEmpty()) {
            showAlert("Input Error", "Please provide a new order status.");
            return;
        }

        try {
            // Create a table object to update
            Table updatedTable = new Table(tableID, newOrderStatus);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(updatedTable);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + tableID))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                loadTables();
                showAlert("Success", "Table updated successfully.");
            } else {
                showAlert("Error", "Failed to update table: " + response.body());
            }
        } catch (Exception e) {
            showAlert("Error updating table", e.getMessage());
        }
    }

    @FXML
    private void deleteTable() {
        String selectedItem = tableList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert("Selection Error", "Please select a table to delete.");
            return;
        }

        Integer tableID = Integer.parseInt(selectedItem.split(":")[1].split("\\(")[0].trim());

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + tableID))
                    .DELETE()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 204) {
                loadTables();
                showAlert("Success", "Table deleted successfully.");
            } else {
                showAlert("Error", "Failed to delete table: " + response.body());
            }
        } catch (Exception e) {
            showAlert("Error deleting table", e.getMessage());
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
