package com.capstone.cartservice.service;

import com.capstone.cartservice.dto.CartRequest;
import com.capstone.cartservice.dto.ProductRequest;
import com.capstone.cartservice.entity.Cart;

import java.util.List;
import java.util.UUID;

public interface CartService {

    List<Cart> getAllCarts();
    Cart getCartById(UUID cartId);
    Cart getCartByUserId(UUID userId);
    Cart createCart(CartRequest cartRequest);
    void updateProduct(CartRequest cartRequest, UUID cartId);
    void addProductToTheCart(ProductRequest productRequest, UUID userId);
    void deleteProductFromTheCart(ProductRequest productRequest, UUID userId) throws Exception;
}
