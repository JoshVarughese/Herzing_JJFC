package com.jjfc.superturboservice.models;

public class Inventory {
    private Integer foodItemID;
    private Integer ingredientStock;
    private String stockAlerts;

    public Inventory() {
    }

    // Constructor with parameters
    public Inventory(Integer foodItemID, Integer ingredientStock, String stockAlerts) {
        this.foodItemID = foodItemID;
        this.ingredientStock = ingredientStock;
        this.stockAlerts = stockAlerts;
    }

    // Getters
    public Integer getFoodItemID() {
        return foodItemID;
    }

    public Integer getIngredientStock() {
        return ingredientStock;
    }

    public String getStockAlerts() {
        return stockAlerts;
    }

    // Setters
    public void setFoodItemID(Integer foodItemID) {
        this.foodItemID = foodItemID;
    }

    public void setIngredientStock(Integer ingredientStock) {
        this.ingredientStock = ingredientStock;
    }

    public void setStockAlerts(String stockAlerts) {
        this.stockAlerts = stockAlerts;
    }
}
