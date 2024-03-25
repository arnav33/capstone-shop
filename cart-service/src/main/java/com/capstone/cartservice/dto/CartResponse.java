package com.capstone.cartservice.dto;

import com.capstone.cartservice.entity.Cart;
import com.capstone.cartservice.entity.Product;

import java.util.List;

public class CartResponse {
    List<Product> products;

    public CartResponse(Cart cart) {
        this.products = cart.getProducts();
    }
}
