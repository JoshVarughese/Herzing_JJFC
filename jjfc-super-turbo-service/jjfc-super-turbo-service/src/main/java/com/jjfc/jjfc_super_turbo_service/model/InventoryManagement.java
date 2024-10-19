package com.jjfc.jjfc_super_turbo_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "InventoryManagement")
public class InventoryManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodItemID;

    @Column(nullable = false)
    private Integer ingredientStock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockAlerts stockAlerts;

    // Getters and setters
    public Integer getFoodItemID() {
        return foodItemID;
    }

    public void setFoodItemID(Integer foodItemID) {
        this.foodItemID = foodItemID;
    }

    public Integer getIngredientStock() {
        return ingredientStock;
    }

    public void setIngredientStock(Integer ingredientStock) {
        this.ingredientStock = ingredientStock;
    }

    public StockAlerts getStockAlerts() {
        return stockAlerts;
    }

    public void setStockAlerts(StockAlerts stockAlerts) {
        this.stockAlerts = stockAlerts;
    }

    public enum StockAlerts {
        Low,
        Medium,
        High
    }
}
