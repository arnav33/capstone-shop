package com.capstone.ordermanagementervice.services;

import com.capstone.ordermanagementervice.dtos.PurchaseRequest;
import com.capstone.ordermanagementervice.entities.Purchase;

import java.util.List;
import java.util.UUID;

public interface PurchaseService {

    List<Purchase> getAllPurchases();
    Purchase getPurchaseById(UUID purchaseId);
    List<Purchase> getAllPurchasesByUserId(UUID userId);
    Purchase createPurchase(PurchaseRequest purchaseRequest);
    void updatePurchase(PurchaseRequest purchaseRequest, UUID purchaseId);
    void deletePurchase(UUID purchaseId);
}
