package com.jjfc.jjfc_super_turbo_service.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "OrderManagement")
public class OrderManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    @Column(nullable = false)
    private Integer tableID;

    @Column(nullable = false)
    private Date orderTime;

    @Column(nullable = false)
    private String orderItems; // Assuming a list of items as a text field

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    // Getters and setters
    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getTableID() {
        return tableID;
    }

    public void setTableID(Integer tableID) {
        this.tableID = tableID;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(String orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public enum OrderStatus {
        Pending,
        InProgress,
        Completed,
        Cancelled
    }
}
