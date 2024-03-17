package com.capstone.paymentservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.paymentservice.entities.ProductReceipt;
import org.springframework.stereotype.Repository;

@Repository
public interface IMysqlProductReceiptRepository extends JpaRepository<ProductReceipt, UUID> {
    
}
