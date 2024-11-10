package com.jjfc.jjfc_super_turbo_service.service;

import com.jjfc.jjfc_super_turbo_service.model.InventoryManagement;
import com.jjfc.jjfc_super_turbo_service.model.InventoryManagement.StockAlerts;
import com.jjfc.jjfc_super_turbo_service.repository.InventoryManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryManagementServiceTest {

    @InjectMocks
    private InventoryManagementService inventoryManagementService;

    @Mock
    private InventoryManagementRepository inventoryManagementRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for createInventoryManagement
    @Test
    void testCreateInventoryManagement() {
        // Basic case
        InventoryManagement inventory = new InventoryManagement();
        inventory.setIngredientStock(50);
        inventory.setStockAlerts(StockAlerts.Medium);
        when(inventoryManagementRepository.save(inventory)).thenReturn(inventory);
        assertNotNull(inventoryManagementService.createInventoryManagement(inventory));

        // Null case 
        InventoryManagement result = inventoryManagementService.createInventoryManagement(null);
        assertNull(result, "Expected result to be null when input is null");

        // Edge case with low stock
        InventoryManagement lowStockInventory = new InventoryManagement();
        lowStockInventory.setIngredientStock(1); // Assuming 1 is the minimum stock
        lowStockInventory.setStockAlerts(StockAlerts.Low);
        when(inventoryManagementRepository.save(lowStockInventory)).thenReturn(lowStockInventory);
        assertNotNull(inventoryManagementService.createInventoryManagement(lowStockInventory));
    }

    // Test for getInventoryManagementById
    @Test
    void testGetInventoryManagementById() {
        // Basic case
        Integer id = 1;
        InventoryManagement inventory = new InventoryManagement();
        inventory.setIngredientStock(100);
        inventory.setStockAlerts(StockAlerts.High);
        when(inventoryManagementRepository.findById(id)).thenReturn(Optional.of(inventory));
        assertEquals(100, inventoryManagementService.getInventoryManagementById(id).getIngredientStock());

        // Null case
        assertNull(inventoryManagementService.getInventoryManagementById(null));

        // Edge case with invalid ID
        Integer invalidId = -1;
        when(inventoryManagementRepository.findById(invalidId)).thenReturn(Optional.empty());
        assertNull(inventoryManagementService.getInventoryManagementById(invalidId));
    }

    // Test for updateInventoryManagement
    @Test
    void testUpdateInventoryManagement() {
        // Basic case
        Integer id = 1;
        InventoryManagement existingInventory = new InventoryManagement();
        existingInventory.setIngredientStock(20);
        existingInventory.setStockAlerts(StockAlerts.Low);

        InventoryManagement updatedInventoryDetails = new InventoryManagement();
        updatedInventoryDetails.setIngredientStock(30);
        updatedInventoryDetails.setStockAlerts(StockAlerts.Medium);

        when(inventoryManagementRepository.findById(id)).thenReturn(Optional.of(existingInventory));
        when(inventoryManagementRepository.save(existingInventory)).thenReturn(existingInventory);

        InventoryManagement updatedInventory = inventoryManagementService.updateInventoryManagement(id, updatedInventoryDetails);
        assertEquals(30, updatedInventory.getIngredientStock());
        assertEquals(StockAlerts.Medium, updatedInventory.getStockAlerts());

        // Null case
        assertThrows(NullPointerException.class, () -> {
            inventoryManagementService.updateInventoryManagement(id, null);
        }, "Expected NullPointerException when inventoryManagementDetails is null");

        // Edge case with non-existent ID
        Integer nonExistentId = 999;
        when(inventoryManagementRepository.findById(nonExistentId)).thenReturn(Optional.empty());
        assertNull(inventoryManagementService.updateInventoryManagement(nonExistentId, updatedInventoryDetails));
    }

    // Test for deleteInventoryManagement
    @Test
    void testDeleteInventoryManagement() {
        // Basic case
        Integer id = 1;
        doNothing().when(inventoryManagementRepository).deleteById(id);
        inventoryManagementService.deleteInventoryManagement(id);
        verify(inventoryManagementRepository, times(1)).deleteById(id);

        // Null case
        assertDoesNotThrow(() -> inventoryManagementService.deleteInventoryManagement(null));

        // Edge case with invalid ID
        Integer invalidId = -1;
        doNothing().when(inventoryManagementRepository).deleteById(invalidId);
        inventoryManagementService.deleteInventoryManagement(invalidId);
        verify(inventoryManagementRepository, times(1)).deleteById(invalidId);
    }

    // Test for getAllInventoryManagement
    @Test
    void testGetAllInventoryManagement() {
        // Basic case
        List<InventoryManagement> inventoryList = new ArrayList<>();
        inventoryList.add(new InventoryManagement());
        inventoryList.add(new InventoryManagement());

        when(inventoryManagementRepository.findAll()).thenReturn(inventoryList);
        assertEquals(2, inventoryManagementService.getAllInventoryManagement().size());

        // Null case
        when(inventoryManagementRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(inventoryManagementService.getAllInventoryManagement().isEmpty());

        // Edge case with large dataset
        List<InventoryManagement> largeList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeList.add(new InventoryManagement());
        }
        when(inventoryManagementRepository.findAll()).thenReturn(largeList);
        assertEquals(1000, inventoryManagementService.getAllInventoryManagement().size());
    }
}
