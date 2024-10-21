package com.jjfc.superturboservice.controllers;

import com.jjfc.superturboservice.models.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class OrderManagementController {

    @FXML
    private TextField tableIDField;

    @FXML
    private TextField orderTimeField;

    @FXML
    private TextField orderItemsField;

    @FXML
    private ComboBox<String> orderStatusComboBox;

    @FXML
    private ListView<Order> ordersListView;

    private ObservableList<Order> orders = FXCollections.observableArrayList();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String BASE_URL = "http://localhost:8080/api/orders";

    @FXML
    private void initialize() {
        orderStatusComboBox.getItems().addAll("Pending", "InProgress", "Completed");
        loadOrders();
    }

    @FXML
    private void loadOrders() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            List<Order> orderList = objectMapper.readValue(response.body(), new TypeReference<List<Order>>() {});

            ordersListView.getItems().clear();
            orders.addAll(orderList);
            ordersListView.setItems(orders);
        } catch (Exception e) {
            showAlert("Error loading orders", e.getMessage());
        }
    }

    @FXML
    private void createOrder() {
        String tableID = tableIDField.getText();
        String orderTimeString = orderTimeField.getText();
        String orderItems = orderItemsField.getText();
        String orderStatus = orderStatusComboBox.getValue();

        if (tableID.isEmpty() || orderTimeString.isEmpty() || orderItems.isEmpty() || orderStatus == null) {
            showAlert("Input Error", "Please fill in all fields.");
            return;
        }

        try {
            Order newOrder = new Order(null, Integer.parseInt(tableID), orderTimeString, orderItems, orderStatus); // Pass null for orderID

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(newOrder);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 201) {
                loadOrders();
                showAlert("Success", "Order added successfully.");
            } else {
                showAlert("Error", "Failed to add order: " + response.body());
            }
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numeric values for table ID.");
        } catch (Exception e) {
            showAlert("Error creating order", e.getMessage());
        }
    }

    @FXML
    private void updateOrder() {
        Order selectedOrder = ordersListView.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            showAlert("Selection Error", "Please select an order to update.");
            return;
        }

        String newOrderTimeString = orderTimeField.getText();
        String newOrderItems = orderItemsField.getText();
        String newOrderStatus = orderStatusComboBox.getValue();

        if (newOrderTimeString.isEmpty() || newOrderItems.isEmpty() || newOrderStatus == null) {
            showAlert("Input Error", "Please provide new order details.");
            return;
        }

        try {
            Order updatedOrder = new Order(selectedOrder.getOrderID(), selectedOrder.getTableID(), newOrderTimeString, newOrderItems, newOrderStatus);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(updatedOrder);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + selectedOrder.getOrderID()))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                loadOrders();
                showAlert("Success", "Order updated successfully.");
            } else {
                showAlert("Error", "Failed to update order: " + response.body());
            }
        } catch (Exception e) {
            showAlert("Error updating order", e.getMessage());
        }
    }

    @FXML
    private void deleteOrder() {
        Order selectedOrder = ordersListView.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            showAlert("Selection Error", "Please select an order to delete.");
            return;
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + selectedOrder.getOrderID()))
                    .DELETE()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 204) {
                loadOrders();
                showAlert("Success", "Order deleted successfully.");
            } else {
                showAlert("Error", "Failed to delete order: " + response.body());
            }
        } catch (Exception e) {
            showAlert("Error deleting order", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
