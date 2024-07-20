package com.capstone.ordermanagementervice.controllers;

import com.capstone.ordermanagementervice.dtos.CreateOrderRequest;
import com.capstone.ordermanagementervice.dtos.OrderRequest;
import com.capstone.ordermanagementervice.entities.ProductOrder;
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
    public List<ProductOrder> getAllOrders() {
        return this.orderService.getAllOrders();
    }
    @GetMapping("{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductOrder getOrdersById(@PathVariable UUID orderId) {
        return this.orderService.getOrdersById(orderId);
    }

    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductOrder> getAllOrdersByUserId(@PathVariable UUID userId) {
        return this.orderService.getAllOrdersByUserId(userId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductOrder createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return this.orderService.createOrder(createOrderRequest);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrder(@RequestBody OrderRequest orderRequest) {
        this.orderService.updateOrder(orderRequest);
    }

    @PutMapping("/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrderStatus(@RequestBody OrderRequest orderRequest) {
        this.orderService.updateOrderStatus(orderRequest);
    }
    @DeleteMapping("{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable UUID orderId) {
        this.orderService.deleteOrder(orderId);
    }
}
