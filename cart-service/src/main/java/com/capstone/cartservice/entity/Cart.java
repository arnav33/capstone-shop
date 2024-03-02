package com.capstone.cartservice.entity;

import com.capstone.cartservice.dto.CartRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    List<Product> products;

    String userId;

    public Cart(CartRequest cartRequest) {
        this.products = new ArrayList<>();
        this.userId = cartRequest.getUserId();
    }
}
