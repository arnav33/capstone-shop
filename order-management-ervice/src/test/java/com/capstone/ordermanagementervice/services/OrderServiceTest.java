package com.capstone.ordermanagementervice.services;

import com.capstone.ordermanagementervice.dtos.CreateOrderRequest;
import com.capstone.ordermanagementervice.dtos.OrderRequest;
import com.capstone.ordermanagementervice.entities.ProductOrder;
import com.capstone.ordermanagementervice.repositories.MysqlOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private MysqlOrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        // Arrange
        when(orderRepository.findAll()).thenReturn(List.of(new ProductOrder(), new ProductOrder()));

        // Act
        List<ProductOrder> result = orderService.getAllOrders();

        // Assert
        assertEquals(2, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrdersById() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        ProductOrder order = new ProductOrder();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // Act
        ProductOrder result = orderService.getOrdersById(orderId);

        // Assert
        assertNotNull(result);
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void testGetOrdersById_NotFound() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            orderService.getOrdersById(orderId);
        });
        assertEquals("Product with the id " + orderId + " not found in the system", exception.getMessage());
    }

    @Test
    void testCreateOrder() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID addressId = UUID.randomUUID();
        UUID cartId = UUID.randomUUID();
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(userId, addressId, cartId);
        ProductOrder order = new ProductOrder(createOrderRequest);
        when(orderRepository.save(any(ProductOrder.class))).thenReturn(order);

        // Act
        ProductOrder result = orderService.createOrder(createOrderRequest);

        // Assert
        assertNotNull(result);
        verify(orderRepository, times(1)).save(any(ProductOrder.class));
    }

    @Test
    void testUpdateOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        OrderRequest orderRequest = new OrderRequest();
        ProductOrder existingOrder = new ProductOrder();
        existingOrder.setId(orderId);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(ProductOrder.class))).thenReturn(existingOrder);

        // Act
        orderService.updateOrder(orderRequest);

        // Assert
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(any(ProductOrder.class));
    }

    @Test
    void testDeleteOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        ProductOrder existingOrder = new ProductOrder();
        existingOrder.setId(orderId);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(ProductOrder.class))).thenReturn(existingOrder);

        // Act
        orderService.deleteOrder(orderId);

        // Assert
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(existingOrder);
        assertTrue(existingOrder.isDeleted());
    }

    @Test
    void testUpdateOrderStatus() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(orderId);
        ProductOrder existingOrder = new ProductOrder();
        existingOrder.setId(orderId);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(ProductOrder.class))).thenReturn(existingOrder);

        // Act
        orderService.updateOrderStatus(orderRequest);

        // Assert
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(existingOrder);
    }
}