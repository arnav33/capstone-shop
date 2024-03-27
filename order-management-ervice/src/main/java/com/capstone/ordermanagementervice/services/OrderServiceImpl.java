package com.capstone.ordermanagementervice.services;

import com.capstone.ordermanagementervice.dtos.CreateOrderRequest;
import com.capstone.ordermanagementervice.dtos.OrderRequest;
import com.capstone.ordermanagementervice.entities.ProductOrder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstone.ordermanagementervice.repositories.MysqlOrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    MysqlOrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(MysqlOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<ProductOrder> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public ProductOrder getOrdersById(UUID orderId) {
        Optional<ProductOrder> op = this.orderRepository.findById(orderId);
        return op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + orderId + " not found in the system"));
    }

    public List<ProductOrder> getAllOrdersByUserId(UUID userId) {
        return this.orderRepository.findOrdersByUserId(userId);
    }

    public ProductOrder createOrder(CreateOrderRequest createOrderRequest) {
        ProductOrder order = new ProductOrder(createOrderRequest);
        this.orderRepository.save(order);
        return order;
    }

    public void updateOrder(OrderRequest orderRequest) {
        UUID orderId = orderRequest.getOrderId();
        ProductOrder order = new ProductOrder(orderRequest);
        Optional<ProductOrder> op = orderRepository.findById(orderId);
        ProductOrder savedOrder = op.orElseThrow(() -> new EntityNotFoundException("Order with the id " + orderId + " not found in the system"));
        order.setId(savedOrder.getId());
        this.orderRepository.save(savedOrder);
        this.orderRepository.save(order);
    }

    public void deleteOrder(UUID orderId) {
        Optional<ProductOrder> op = this.orderRepository.findById(orderId);
        ProductOrder savedOrder = op.orElseThrow(() -> new EntityNotFoundException("Order with the id " + orderId + " not found in the system"));
        savedOrder.setDeleted(true);
        this.orderRepository.save(savedOrder);
    }

    public void updateOrderStatus(OrderRequest orderRequest) {
        Optional<ProductOrder> optionalOrder = this.orderRepository.findById(orderRequest.getOrderId());
        ProductOrder savedOrder = optionalOrder.orElseThrow(() -> new EntityNotFoundException("Order with the id " + orderRequest.getOrderId() + " not found in the system"));
        savedOrder.setPaymentStatus(orderRequest.getPaymentStatus());
        this.orderRepository.save(savedOrder);
    }
}
