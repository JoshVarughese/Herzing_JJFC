package com.jjfc.jjfc_super_turbo_service.service;

import com.jjfc.jjfc_super_turbo_service.model.TableService;
import com.jjfc.jjfc_super_turbo_service.repository.TableServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceService {
    @Autowired
    private TableServiceRepository tableServiceRepository;

    // Create a new TableService record
    public TableService createTableService(TableService tableService) {
        return tableServiceRepository.save(tableService);
    }

    // Read an existing TableService record
    public TableService getTableServiceById(Integer id) {
        return tableServiceRepository.findById(id).orElse(null);
    }

    // Update an existing TableService record
    public TableService updateTableService(Integer id, TableService tableServiceDetails) {
        TableService tableService = tableServiceRepository.findById(id).orElse(null);
        if (tableService != null) {
            tableService.setOrderStatus(tableServiceDetails.getOrderStatus());
            return tableServiceRepository.save(tableService);
        }
        return null;
    }

    // Delete a TableService record
    public void deleteTableService(Integer id) {
        tableServiceRepository.deleteById(id);
    }

    // Get all TableService records
    public List<TableService> getAllTableServices() {
        return tableServiceRepository.findAll();
    }
}
