package com.capstone.cartservice.service;

import com.capstone.cartservice.dto.PurchaseRequest;
import com.capstone.cartservice.entity.Purchase;
import com.capstone.cartservice.repository.MysqlPurchaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    MysqlPurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(MysqlPurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAllPurchases() {
        return this.purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(UUID purchaseId) {
        Optional<Purchase> op = this.purchaseRepository.findById(purchaseId);
        return op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + purchaseId + " not found in the system"));
    }

    public List<Purchase> getAllPurchasesByUserId(UUID userId) {
        return this.purchaseRepository.findPurchasesByUserId(userId);
    }

    public Purchase createPurchase(PurchaseRequest purchaseRequest) {
        Purchase purchase = new Purchase(purchaseRequest);
        this.purchaseRepository.save(purchase);
        return purchase;
    }

    public void updatePurchase(PurchaseRequest purchaseRequest, UUID purchaseId) {
        Purchase purchase = new Purchase(purchaseRequest);
        Optional<Purchase> op = purchaseRepository.findById(purchaseId);
        Purchase savedPurchase = op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + purchaseId + " not found in the system"));
        purchase.setId(savedPurchase.getId());
        this.purchaseRepository.save(purchase);
    }

    public void deletePurchase(UUID purchaseId) {
        Optional<Purchase> op = this.purchaseRepository.findById(purchaseId);
        Purchase savedPurchase = op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + purchaseId + " not found in the system"));
        savedPurchase.setDeleted(true);
        this.purchaseRepository.save(savedPurchase);
    }
}
