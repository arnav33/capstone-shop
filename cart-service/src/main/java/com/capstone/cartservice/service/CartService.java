package com.capstone.cartservice.service;

import com.capstone.cartservice.dto.CartRequest;
import com.capstone.cartservice.dto.ProductRequest;
import com.capstone.cartservice.entity.Cart;
import com.capstone.cartservice.entity.Product;
import com.capstone.cartservice.repository.IMysqlCartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    IMysqlCartRepository cartRepository;

    @Autowired
    CartService(IMysqlCartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts() {
        return this.cartRepository.findAll();
    }

    public Cart getCartById(UUID cartId) {
        Optional<Cart> op = this.cartRepository.findById(cartId);
        return op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + cartId + " not found in the system"));
    }

    public Cart getCartByUserId(String userId) {
        return this.cartRepository.findCartByUserId(userId);
    }

    public Cart createCart(CartRequest cartRequest) throws URISyntaxException, IOException, InterruptedException {
        Cart cart = new Cart(cartRequest);
        this.cartRepository.save(cart);
        return cart;
    }

    public void updateProduct(CartRequest cartRequest, UUID cartId) {
        Cart cart = new Cart(cartRequest);
        Optional<Cart> op = cartRepository.findById(cartId);
        Cart savedCart = op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + cartId + " not found in the system"));
        cart.setId(savedCart.getId());
        this.cartRepository.save(cart);
    }

    public void addProductToTheCart(ProductRequest productRequest, String userId) {
        Product product = new Product(productRequest);
        Cart cart = cartRepository.findCartByUserId(userId);
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }
}
