package com.capstone.ordermanagementervice.controllers;

import com.capstone.ordermanagementervice.dtos.OrderRequest;
import com.capstone.ordermanagementervice.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.capstone.ordermanagementervice.services.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Order> getAllOrders() {
        return this.orderService.getAllOrders();
    }
    @GetMapping("{orderId}")
    @ResponseStatus(HttpStatus.OK)
    Order getOrdersById(@PathVariable UUID orderId) {
        return this.orderService.getOrdersById(orderId);
    }

    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    List<Order> getAllOrdersByUserId(@PathVariable UUID userId) {
        return this.orderService.getAllOrdersByUserId(userId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Order createOrder(OrderRequest orderRequest) {
        return this.orderService.createOrder(orderRequest);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateOrder(OrderRequest orderRequest) {
        this.orderService.updateOrder(orderRequest);
    }

    @PutMapping("/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateOrderStatus(OrderRequest orderRequest) {
        this.orderService.updateOrderStatus(orderRequest);
    }
    @DeleteMapping("{orderId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteOrder(@PathVariable UUID orderId) {
        this.orderService.deleteOrder(orderId);
    }
}
