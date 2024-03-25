package com.capstone.cartservice.repository;

import com.capstone.cartservice.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MysqlPurchaseRepository extends JpaRepository<Purchase, UUID> {

    List<Purchase> findPurchasesByUserId(UUID userId);
}
