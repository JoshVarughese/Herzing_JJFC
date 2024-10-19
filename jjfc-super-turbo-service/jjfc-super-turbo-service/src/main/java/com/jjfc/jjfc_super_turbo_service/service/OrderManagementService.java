package com.jjfc.jjfc_super_turbo_service.service;

import com.jjfc.jjfc_super_turbo_service.model.OrderManagement;
import com.jjfc.jjfc_super_turbo_service.repository.OrderManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderManagementService {
    @Autowired
    private OrderManagementRepository orderManagementRepository;

    // Create a new OrderManagement record
    public OrderManagement createOrderManagement(OrderManagement orderManagement) {
        return orderManagementRepository.save(orderManagement);
    }

    // Read an existing OrderManagement record
    public OrderManagement getOrderManagementById(Integer id) {
        return orderManagementRepository.findById(id).orElse(null);
    }

    // Update an existing OrderManagement record
    public OrderManagement updateOrderManagement(Integer id, OrderManagement orderManagementDetails) {
        OrderManagement orderManagement = orderManagementRepository.findById(id).orElse(null);
        if (orderManagement != null) {
            orderManagement.setTableID(orderManagementDetails.getTableID());
            orderManagement.setOrderTime(orderManagementDetails.getOrderTime());
            orderManagement.setOrderItems(orderManagementDetails.getOrderItems());
            orderManagement.setOrderStatus(orderManagementDetails.getOrderStatus());
            return orderManagementRepository.save(orderManagement);
        }
        return null;
    }

    // Delete an OrderManagement record
    public void deleteOrderManagement(Integer id) {
        orderManagementRepository.deleteById(id);
    }

    // Get all OrderManagement records
    public List<OrderManagement> getAllOrderManagements() {
        return orderManagementRepository.findAll();
    }
}
