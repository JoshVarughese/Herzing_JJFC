package com.jjfc.jjfc_super_turbo_service.service;

import com.jjfc.jjfc_super_turbo_service.model.OrderManagement;
import com.jjfc.jjfc_super_turbo_service.repository.OrderManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderManagementServiceTest {

    @Mock
    private OrderManagementRepository orderManagementRepository;

    @InjectMocks
    private OrderManagementService orderManagementService;

    private OrderManagement orderManagement;

    @BeforeEach
    void setUp() {
        orderManagement = new OrderManagement();
        orderManagement.setOrderID(1);
        orderManagement.setTableID(1);
        orderManagement.setOrderTime(new Date());
        orderManagement.setOrderItems("Pizza, Soda");
        orderManagement.setOrderStatus(OrderManagement.OrderStatus.Pending);
    }

    // Test case for creating an order
    @Test
    void testCreateOrderManagement() {
        // Basic case
        when(orderManagementRepository.save(any(OrderManagement.class))).thenReturn(orderManagement);
        OrderManagement createdOrder = orderManagementService.createOrderManagement(orderManagement);

        assertNotNull(createdOrder);
        assertEquals(orderManagement.getOrderID(), createdOrder.getOrderID());
        verify(orderManagementRepository, times(1)).save(any(OrderManagement.class));

        // Null case
        assertNull(orderManagementService.createOrderManagement(null));

        // Edge case: Empty fields
        OrderManagement emptyOrder = new OrderManagement();  // Fields not set
        when(orderManagementRepository.save(emptyOrder)).thenReturn(emptyOrder);
        assertNotNull(orderManagementService.createOrderManagement(emptyOrder));
    }

    // Test case for getting an order
    @Test
    void testGetOrderManagementById() {
        // Basic case
        when(orderManagementRepository.findById(anyInt())).thenReturn(Optional.of(orderManagement));
        OrderManagement foundOrder = orderManagementService.getOrderManagementById(1);

        assertNotNull(foundOrder);
        assertEquals(orderManagement.getOrderID(), foundOrder.getOrderID());
        verify(orderManagementRepository, times(1)).findById(anyInt());

        // Null case
        assertNull(orderManagementService.getOrderManagementById(null));

        // Edge case: Invalid ID
        when(orderManagementRepository.findById(-1)).thenReturn(Optional.empty());
        assertNull(orderManagementService.getOrderManagementById(-1));
    }

    // Test case for updating an order
    @Test
    void testUpdateOrderManagement() {
        OrderManagement updatedOrder = new OrderManagement();
        updatedOrder.setTableID(2);
        updatedOrder.setOrderTime(new Date());
        updatedOrder.setOrderItems("Burger, Fries");
        updatedOrder.setOrderStatus(OrderManagement.OrderStatus.InProgress);

        // Basic case
        when(orderManagementRepository.findById(anyInt())).thenReturn(Optional.of(orderManagement));
        when(orderManagementRepository.save(any(OrderManagement.class))).thenReturn(updatedOrder);

        OrderManagement result = orderManagementService.updateOrderManagement(1, updatedOrder);

        assertNotNull(result);
        assertEquals(updatedOrder.getOrderItems(), result.getOrderItems());
        assertEquals(updatedOrder.getOrderStatus(), result.getOrderStatus());
        verify(orderManagementRepository, times(1)).save(any(OrderManagement.class));

        // Null ID case
        assertNull(orderManagementService.updateOrderManagement(null, updatedOrder));

        // Edge case: Invalid ID
        when(orderManagementRepository.findById(-1)).thenReturn(Optional.empty());
        assertNull(orderManagementService.updateOrderManagement(-1, updatedOrder));
    }

    // Test case for deleting an order
    @Test
    void testDeleteOrderManagement() {
        // Basic case
        doNothing().when(orderManagementRepository).deleteById(anyInt());
        orderManagementService.deleteOrderManagement(1);
        verify(orderManagementRepository, times(1)).deleteById(anyInt());

        // Null case
        assertDoesNotThrow(() -> orderManagementService.deleteOrderManagement(null));

        // Edge case: Invalid ID
        doNothing().when(orderManagementRepository).deleteById(-1);
        orderManagementService.deleteOrderManagement(-1);
        verify(orderManagementRepository, times(1)).deleteById(-1);
    }

    // Test case for getting all orders
    @Test
    void testGetAllOrderManagements() {
        // Basic case
        when(orderManagementRepository.findAll()).thenReturn(Arrays.asList(orderManagement, new OrderManagement()));
        var allOrders = orderManagementService.getAllOrderManagements();

        assertNotNull(allOrders);
        assertEquals(2, allOrders.size());
        verify(orderManagementRepository, times(1)).findAll();

        // Reset mock before the next call (to prevent TooManyActualInvocations error)
        reset(orderManagementRepository);

        // Null case
        when(orderManagementRepository.findAll()).thenReturn(Arrays.asList());
        allOrders = orderManagementService.getAllOrderManagements();

        assertNotNull(allOrders);
        assertTrue(allOrders.isEmpty(), "The list should be empty.");
        verify(orderManagementRepository, times(1)).findAll();

        // Reset again for the large list case (to prevent TooManyActualInvocations error)
        reset(orderManagementRepository);

        // Edge case: Large list
        when(orderManagementRepository.findAll()).thenReturn(Arrays.asList(new OrderManagement(), new OrderManagement(), new OrderManagement()));
        allOrders = orderManagementService.getAllOrderManagements();

        assertNotNull(allOrders);
        assertEquals(3, allOrders.size());
        verify(orderManagementRepository, times(1)).findAll();
    }

}
