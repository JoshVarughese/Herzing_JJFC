package com.jjfc.jjfc_super_turbo_service.service;

import com.jjfc.jjfc_super_turbo_service.model.InventoryManagement;
import com.jjfc.jjfc_super_turbo_service.repository.InventoryManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryManagementService {
    @Autowired
    private InventoryManagementRepository inventoryManagementRepository;

    // Create a new InventoryManagement record
    public InventoryManagement createInventoryManagement(InventoryManagement inventoryManagement) {
        return inventoryManagementRepository.save(inventoryManagement);
    }

    // Read an existing InventoryManagement record
    public InventoryManagement getInventoryManagementById(Integer id) {
        return inventoryManagementRepository.findById(id).orElse(null);
    }

    // Update an existing InventoryManagement record
    public InventoryManagement updateInventoryManagement(Integer id, InventoryManagement inventoryManagementDetails) {
        InventoryManagement inventoryManagement = inventoryManagementRepository.findById(id).orElse(null);
        if (inventoryManagement != null) {
            inventoryManagement.setIngredientStock(inventoryManagementDetails.getIngredientStock());
            inventoryManagement.setStockAlerts(inventoryManagementDetails.getStockAlerts());
            return inventoryManagementRepository.save(inventoryManagement);
        }
        return null;
    }

    // Delete an InventoryManagement record
    public void deleteInventoryManagement(Integer id) {
        inventoryManagementRepository.deleteById(id);
    }

    // Get all InventoryManagement records
    public List<InventoryManagement> getAllInventoryManagement() {
        return inventoryManagementRepository.findAll();
    }
}
