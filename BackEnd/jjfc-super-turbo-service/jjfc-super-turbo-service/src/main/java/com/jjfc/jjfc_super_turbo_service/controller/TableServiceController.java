package com.jjfc.jjfc_super_turbo_service.controller;

import com.jjfc.jjfc_super_turbo_service.model.TableService;
import com.jjfc.jjfc_super_turbo_service.service.TableServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableServiceController {
    @Autowired
    private TableServiceService tableServiceService;

    // Create a new TableService
    @PostMapping
    public TableService createTableService(@RequestBody TableService tableService) {
        return tableServiceService.createTableService(tableService);
    }

    // Get a TableService by ID
    @GetMapping("/{id}")
    public ResponseEntity<TableService> getTableService(@PathVariable Integer id) {
        TableService tableService = tableServiceService.getTableServiceById(id);
        if (tableService != null) {
            return ResponseEntity.ok(tableService);
        }
        return ResponseEntity.notFound().build();
    }

    // Update an existing TableService
    @PutMapping("/{id}")
    public ResponseEntity<TableService> updateTableService(@PathVariable Integer id, @RequestBody TableService tableServiceDetails) {
        TableService updatedTableService = tableServiceService.updateTableService(id, tableServiceDetails);
        if (updatedTableService != null) {
            return ResponseEntity.ok(updatedTableService);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a TableService
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableService(@PathVariable Integer id) {
        tableServiceService.deleteTableService(id);
        return ResponseEntity.noContent().build();
    }

    // Get all TableServices
    @GetMapping
    public List<TableService> getAllTableServices() {
        return tableServiceService.getAllTableServices();
    }
}
