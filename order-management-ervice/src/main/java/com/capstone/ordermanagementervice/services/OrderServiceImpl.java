package com.capstone.ordermanagementervice.services;

import com.capstone.ordermanagementervice.dtos.OrderRequest;
import com.capstone.ordermanagementervice.entities.Order;
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

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Order getOrdersById(UUID purchaseId) {
        Optional<Order> op = this.orderRepository.findById(purchaseId);
        return op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + purchaseId + " not found in the system"));
    }

    public List<Order> getAllOrdersByUserId(UUID userId) {
        return this.orderRepository.findOrdersByUserId(userId);
    }

    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order(orderRequest);
        order.getTransactionIds().add(UUID.randomUUID());
        this.orderRepository.save(order);
        return order;
    }

    public void updateOrder(OrderRequest orderRequest, UUID purchaseId) {
        Order order = new Order(orderRequest);
        Optional<Order> op = orderRepository.findById(purchaseId);
        Order savedPurchase = op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + purchaseId + " not found in the system"));
        order.setId(savedPurchase.getId());
        UUID newTransactionId = UUID.randomUUID();
        savedPurchase.getTransactionIds().add(newTransactionId);
        this.orderRepository.save(savedPurchase);
        order.getTransactionIds().add(newTransactionId);
        this.orderRepository.save(order);
    }

    public void deleteOrder(UUID purchaseId) {
        Optional<Order> op = this.orderRepository.findById(purchaseId);
        Order savedOrder = op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + purchaseId + " not found in the system"));
        savedOrder.setDeleted(true);
        this.orderRepository.save(savedOrder);
    }
}
