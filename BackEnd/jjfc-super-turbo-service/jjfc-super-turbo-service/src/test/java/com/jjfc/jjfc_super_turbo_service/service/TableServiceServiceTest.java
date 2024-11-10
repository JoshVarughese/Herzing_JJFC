package com.jjfc.jjfc_super_turbo_service.service;

import com.jjfc.jjfc_super_turbo_service.model.TableService;
import com.jjfc.jjfc_super_turbo_service.repository.TableServiceRepository;
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

class TableServiceServiceTest {

    @InjectMocks
    private TableServiceService tableServiceService;

    @Mock
    private TableServiceRepository tableServiceRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for createTableService
    @Test
    void testCreateTableService() {
        // Basic case
        TableService table = new TableService();
        table.setOrderStatus(TableService.OrderStatus.Available);
        when(tableServiceRepository.save(table)).thenReturn(table);
        assertNotNull(tableServiceService.createTableService(table));


        assertNull(tableServiceService.createTableService(null));  // Null Case

        // Edge case
        TableService emptyTable = new TableService();
        when(tableServiceRepository.save(emptyTable)).thenReturn(emptyTable);
        assertNotNull(tableServiceService.createTableService(emptyTable));
    }

    // Test for getTableServiceById
    @Test
    void testGetTableServiceById() {
        // Basic case
        Integer id = 1;
        TableService table = new TableService();
        table.setOrderStatus(TableService.OrderStatus.Occupied);
        when(tableServiceRepository.findById(id)).thenReturn(Optional.of(table));
        assertEquals("Occupied", tableServiceService.getTableServiceById(id).getOrderStatus().toString());

        // Null case
        assertNull(tableServiceService.getTableServiceById(null));

        // Edge case
        Integer invalidId = -1;
        when(tableServiceRepository.findById(invalidId)).thenReturn(Optional.empty());
        assertNull(tableServiceService.getTableServiceById(invalidId));
    }

    // Test for updateTableService
    @Test
    void testUpdateTableService() {
        // Basic case
        Integer id = 1;
        TableService existingTable = new TableService();
        existingTable.setOrderStatus(TableService.OrderStatus.Available);
        TableService updatedTableDetails = new TableService();
        updatedTableDetails.setOrderStatus(TableService.OrderStatus.Reserved);

        when(tableServiceRepository.findById(id)).thenReturn(Optional.of(existingTable));
        when(tableServiceRepository.save(existingTable)).thenReturn(existingTable);

        TableService updatedTable = tableServiceService.updateTableService(id, updatedTableDetails);
        assertEquals("Reserved", updatedTable.getOrderStatus().toString());
    }


    // Test for deleteTableService
    @Test
    void testDeleteTableService() {
        // Basic case
        Integer id = 1;
        doNothing().when(tableServiceRepository).deleteById(id);
        tableServiceService.deleteTableService(id);
        verify(tableServiceRepository, times(1)).deleteById(id);

        // Null case
        assertDoesNotThrow(() -> tableServiceService.deleteTableService(null));

        // Edge case
        Integer invalidId = -1;
        doNothing().when(tableServiceRepository).deleteById(invalidId);
        tableServiceService.deleteTableService(invalidId);
        verify(tableServiceRepository, times(1)).deleteById(invalidId);
    }

    // Test for getAllTableServices
    @Test
    void testGetAllTableServices() {
        // Basic case
        List<TableService> tableList = new ArrayList<>();
        tableList.add(new TableService());
        tableList.add(new TableService());

        when(tableServiceRepository.findAll()).thenReturn(tableList);
        assertEquals(2, tableServiceService.getAllTableServices().size());

        // Null case
        when(tableServiceRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(tableServiceService.getAllTableServices().isEmpty());

        // Edge case
        List<TableService> largeList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeList.add(new TableService());
        }
        when(tableServiceRepository.findAll()).thenReturn(largeList);
        assertEquals(1000, tableServiceService.getAllTableServices().size());
    }
}
