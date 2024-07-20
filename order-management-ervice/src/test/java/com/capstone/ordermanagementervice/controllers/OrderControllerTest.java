package com.capstone.ordermanagementervice.controllers;

import com.capstone.ordermanagementervice.dtos.CreateOrderRequest;
import com.capstone.ordermanagementervice.dtos.OrderRequest;
import com.capstone.ordermanagementervice.entities.ProductOrder;
import com.capstone.ordermanagementervice.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        // Arrange
        List<ProductOrder> orders = Arrays.asList(new ProductOrder(), new ProductOrder());
        when(orderService.getAllOrders()).thenReturn(orders);

        // Act
        List<ProductOrder> result = orderController.getAllOrders();

        // Assert
        assertEquals(2, result.size());
        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void testGetOrdersById() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        ProductOrder order = new ProductOrder();
        when(orderService.getOrdersById(orderId)).thenReturn(order);

        // Act
        ProductOrder result = orderController.getOrdersById(orderId);

        // Assert
        assertNotNull(result);
        verify(orderService, times(1)).getOrdersById(orderId);
    }

    @Test
    void testGetAllOrdersByUserId() {
        // Arrange
        UUID userId = UUID.randomUUID();
        List<ProductOrder> orders = Arrays.asList(new ProductOrder(), new ProductOrder());
        when(orderService.getAllOrdersByUserId(userId)).thenReturn(orders);

        // Act
        List<ProductOrder> result = orderController.getAllOrdersByUserId(userId);

        // Assert
        assertEquals(2, result.size());
        verify(orderService, times(1)).getAllOrdersByUserId(userId);
    }

    @Test
    void testCreateOrder() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID addressId = UUID.randomUUID();
        UUID cartId = UUID.randomUUID();
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(userId, addressId, cartId);
        ProductOrder createdOrder = new ProductOrder(createOrderRequest);
        when(orderService.createOrder(createOrderRequest)).thenReturn(createdOrder);

        // Act
        ProductOrder result = orderController.createOrder(createOrderRequest);

        // Assert
        assertNotNull(result);
        verify(orderService, times(1)).createOrder(createOrderRequest);
    }

    @Test
    void testUpdateOrder() {
        // Arrange
        OrderRequest orderRequest = new OrderRequest();
        doNothing().when(orderService).updateOrder(orderRequest);

        // Act
        orderController.updateOrder(orderRequest);

        // Assert
        verify(orderService, times(1)).updateOrder(orderRequest);
    }

    @Test
    void testDeleteOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        doNothing().when(orderService).deleteOrder(orderId);

        // Act
        orderController.deleteOrder(orderId);

        // Assert
        verify(orderService, times(1)).deleteOrder(orderId);
    }
}