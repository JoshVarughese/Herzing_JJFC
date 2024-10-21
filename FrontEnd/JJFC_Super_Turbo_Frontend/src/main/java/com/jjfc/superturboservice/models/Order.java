package com.jjfc.superturboservice.models;

import java.time.OffsetDateTime;

public class Order {
    private String orderTime;
    private Integer tableID;
    private String orderItems;
    private String orderStatus;
    private Long orderID;

    public Order() {
    }

    public Order(Long orderID, Integer tableID, String orderTime, String orderItems, String orderStatus) {
        this.orderID = orderID;
        this.tableID = tableID;
        this.orderTime = orderTime;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
    }

    // Getters and setters
    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(String orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    // Override toString() method for better representation in ListView
    @Override
    public String toString() {
        return "Order ID: " + orderID + ", Table ID: " + tableID + ", Time: " + orderTime +
                ", Items: " + orderItems + ", Status: " + orderStatus;
    }
}
