package com.capstone.cartservice.service;

import com.capstone.cartservice.dto.PurchaseRequest;
import com.capstone.cartservice.entity.Purchase;

import java.util.List;
import java.util.UUID;

public interface PurchaseService {

    List<Purchase> getAllPurchases();
    Purchase getPurchaseById(UUID purchaseId);
    List<Purchase> getAllPurchasesByUserId(UUID userId);
    Purchase createPurchase(PurchaseRequest purchaseRequest);
    List<Purchase> getPurchasesByUserId(UUID userId);
    void updatePurchase(PurchaseRequest purchaseRequest, UUID purchaseId);
    void deletePurchase(UUID purchaseId);
}
