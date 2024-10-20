package com.jjfc.superturboservice.models;

public class Table {
    private Integer tableID;
    private String orderStatus;

    // No-argument constructor
    public Table() {
    }

    // Constructor with parameters
    public Table(Integer tableID, String orderStatus) {
        this.tableID = tableID;
        this.orderStatus = orderStatus;
    }

    // Getters
    public Integer getTableID() {
        return tableID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    // Setters
    public void setTableID(Integer tableID) {
        this.tableID = tableID;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
