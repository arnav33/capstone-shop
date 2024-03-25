package com.capstone.ordermanagementervice.controllers;

import com.capstone.ordermanagementervice.dtos.PurchaseRequest;
import com.capstone.ordermanagementervice.entities.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.capstone.ordermanagementervice.services.PurchaseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

    PurchaseService purchaseService;

    @Autowired
    PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Purchase> getAllPurchases() {
        return this.purchaseService.getAllPurchases();
    }
    @GetMapping("{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    Purchase getPurchasesById(@PathVariable UUID purchaseId) {
        return this.purchaseService.getPurchaseById(purchaseId);
    }

    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    List<Purchase> getAllPurchasesByUserId(@PathVariable UUID userId) {
        return this.purchaseService.getAllPurchasesByUserId(userId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Purchase createCart(PurchaseRequest purchaseRequest) {
        return this.purchaseService.createPurchase(purchaseRequest);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateProduct(PurchaseRequest purchaseRequest, UUID cartId) {
        this.purchaseService.updatePurchase(purchaseRequest, cartId);
    }
    @DeleteMapping("{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteProductFromTheCart(@PathVariable UUID purchaseId) {
        this.purchaseService.deletePurchase(purchaseId);
    }
}