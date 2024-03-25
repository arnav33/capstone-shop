package com.capstone.ordermanagementervice.services;

import com.capstone.ordermanagementervice.dtos.OrderRequest;
import com.capstone.ordermanagementervice.entities.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<Order> getAllOrders();
    Order getOrdersById(UUID orderId);
    List<Order> getAllOrdersByUserId(UUID userId);
    Order createOrder(OrderRequest orderRequest);
    void updateOrder(OrderRequest orderRequest, UUID purchaseId);
    void deleteOrder(UUID purchaseId);
}
