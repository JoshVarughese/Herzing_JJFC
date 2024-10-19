package com.jjfc.jjfc_super_turbo_service.controller;

import com.jjfc.jjfc_super_turbo_service.model.InventoryManagement;
import com.jjfc.jjfc_super_turbo_service.service.InventoryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryManagementController {
    @Autowired
    private InventoryManagementService inventoryManagementService;

    // Create a new InventoryManagement
    @PostMapping
    public InventoryManagement createInventoryManagement(@RequestBody InventoryManagement inventoryManagement) {
        return inventoryManagementService.createInventoryManagement(inventoryManagement);
    }

    // Get an InventoryManagement by ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryManagement> getInventoryManagement(@PathVariable Integer id) {
        InventoryManagement inventoryManagement = inventoryManagementService.getInventoryManagementById(id);
        if (inventoryManagement != null) {
            return ResponseEntity.ok(inventoryManagement);
        }
        return ResponseEntity.notFound().build();
    }

    // Update an existing InventoryManagement
    @PutMapping("/{id}")
    public ResponseEntity<InventoryManagement> updateInventoryManagement(@PathVariable Integer id, @RequestBody InventoryManagement inventoryManagementDetails) {
        InventoryManagement updatedInventoryManagement = inventoryManagementService.updateInventoryManagement(id, inventoryManagementDetails);
        if (updatedInventoryManagement != null) {
            return ResponseEntity.ok(updatedInventoryManagement);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete an InventoryManagement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryManagement(@PathVariable Integer id) {
        inventoryManagementService.deleteInventoryManagement(id);
        return ResponseEntity.noContent().build();
    }

    // Get all InventoryManagement records
    @GetMapping
    public List<InventoryManagement> getAllInventoryManagement() {
        return inventoryManagementService.getAllInventoryManagement();
    }
}
