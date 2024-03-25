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
@RequestMapping("/api/v1/purchase")
public class OrderController {

    OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Order> getAllPurchases() {
        return this.orderService.getAllOrders();
    }
    @GetMapping("{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    Order getPurchasesById(@PathVariable UUID purchaseId) {
        return this.orderService.getOrdersById(purchaseId);
    }

    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    List<Order> getAllPurchasesByUserId(@PathVariable UUID userId) {
        return this.orderService.getAllOrdersByUserId(userId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Order createCart(OrderRequest orderRequest) {
        return this.orderService.createOrder(orderRequest);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateProduct(OrderRequest orderRequest, UUID cartId) {
        this.orderService.updateOrder(orderRequest, cartId);
    }
    @DeleteMapping("{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteProductFromTheCart(@PathVariable UUID purchaseId) {
        this.orderService.deleteOrder(purchaseId);
    }
}
