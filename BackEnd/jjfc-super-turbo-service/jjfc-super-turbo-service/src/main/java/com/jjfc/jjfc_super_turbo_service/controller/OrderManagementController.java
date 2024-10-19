package com.jjfc.jjfc_super_turbo_service.controller;

import com.jjfc.jjfc_super_turbo_service.model.OrderManagement;
import com.jjfc.jjfc_super_turbo_service.service.OrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderManagementController {
    @Autowired
    private OrderManagementService orderManagementService;

    // Create a new OrderManagement
    @PostMapping
    public OrderManagement createOrderManagement(@RequestBody OrderManagement orderManagement) {
        return orderManagementService.createOrderManagement(orderManagement);
    }

    // Get an OrderManagement by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderManagement> getOrderManagement(@PathVariable Integer id) {
        OrderManagement orderManagement = orderManagementService.getOrderManagementById(id);
        if (orderManagement != null) {
            return ResponseEntity.ok(orderManagement);
        }
        return ResponseEntity.notFound().build();
    }

    // Update an existing OrderManagement
    @PutMapping("/{id}")
    public ResponseEntity<OrderManagement> updateOrderManagement(@PathVariable Integer id, @RequestBody OrderManagement orderManagementDetails) {
        OrderManagement updatedOrderManagement = orderManagementService.updateOrderManagement(id, orderManagementDetails);
        if (updatedOrderManagement != null) {
            return ResponseEntity.ok(updatedOrderManagement);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete an OrderManagement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderManagement(@PathVariable Integer id) {
        orderManagementService.deleteOrderManagement(id);
        return ResponseEntity.noContent().build();
    }

    // Get all OrderManagement records
    @GetMapping
    public List<OrderManagement> getAllOrderManagements() {
        return orderManagementService.getAllOrderManagements();
    }
}
