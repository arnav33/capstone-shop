package com.capstone.ordermanagementervice.services;

import com.capstone.ordermanagementervice.dtos.CreateOrderRequest;
import com.capstone.ordermanagementervice.dtos.OrderRequest;
import com.capstone.ordermanagementervice.entities.ProductOrder;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<ProductOrder> getAllOrders();
    ProductOrder getOrdersById(UUID orderId);
    List<ProductOrder> getAllOrdersByUserId(UUID userId);
    ProductOrder createOrder(CreateOrderRequest createOrderRequest);
    void updateOrder(OrderRequest orderRequest);
    void deleteOrder(UUID orderId);
    void updateOrderStatus(OrderRequest orderRequest);
}
