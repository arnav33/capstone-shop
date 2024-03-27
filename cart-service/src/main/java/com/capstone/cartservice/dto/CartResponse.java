package com.capstone.cartservice.dto;

import com.capstone.cartservice.entity.Cart;
import com.capstone.cartservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    UUID id;
    List<Product> products;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.products = cart.getProducts();
    }
}
