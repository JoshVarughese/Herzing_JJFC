package com.jjfc.jjfc_super_turbo_service.repository;

import com.jjfc.jjfc_super_turbo_service.model.OrderManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderManagementRepository extends JpaRepository<OrderManagement, Integer> {
}
