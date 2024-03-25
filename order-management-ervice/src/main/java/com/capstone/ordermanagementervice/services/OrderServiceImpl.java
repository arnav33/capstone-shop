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

    public Order getOrdersById(UUID orderId) {
        Optional<Order> op = this.orderRepository.findById(orderId);
        return op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + orderId + " not found in the system"));
    }

    public List<Order> getAllOrdersByUserId(UUID userId) {
        return this.orderRepository.findOrdersByUserId(userId);
    }

    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order(orderRequest);
        this.orderRepository.save(order);
        return order;
    }

    public void updateOrder(OrderRequest orderRequest) {
        UUID orderId = orderRequest.getOrderId();
        Order order = new Order(orderRequest);
        Optional<Order> op = orderRepository.findById(orderId);
        Order savedOrder = op.orElseThrow(() -> new EntityNotFoundException("Order with the id " + orderId + " not found in the system"));
        order.setId(savedOrder.getId());
        this.orderRepository.save(savedOrder);
        this.orderRepository.save(order);
    }

    public void deleteOrder(UUID orderId) {
        Optional<Order> op = this.orderRepository.findById(orderId);
        Order savedOrder = op.orElseThrow(() -> new EntityNotFoundException("Order with the id " + orderId + " not found in the system"));
        savedOrder.setDeleted(true);
        this.orderRepository.save(savedOrder);
    }

    public void updateOrderStatus(OrderRequest orderRequest) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderRequest.getOrderId());
        Order savedOrder = optionalOrder.orElseThrow(() -> new EntityNotFoundException("Order with the id " + orderRequest.getOrderId() + " not found in the system"));
        savedOrder.setPaymentStatus(orderRequest.getPaymentStatus());
        this.orderRepository.save(savedOrder);
    }
}
