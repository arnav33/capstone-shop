package com.capstone.cartservice.repository;

import com.capstone.cartservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMysqlCartRepository extends JpaRepository<Cart, UUID> {

    Cart findCartByUserId(String userId);
}
