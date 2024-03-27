package com.capstone.ordermanagementervice.repositories;

import com.capstone.ordermanagementervice.entities.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MysqlOrderRepository extends JpaRepository<ProductOrder, UUID> {

    List<ProductOrder> findOrdersByUserId(UUID userId);
}
