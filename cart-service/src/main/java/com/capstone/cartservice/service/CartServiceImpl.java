package com.capstone.cartservice.service;

import com.capstone.cartservice.dto.CartRequest;
import com.capstone.cartservice.dto.ProductRequest;
import com.capstone.cartservice.entity.Cart;
import com.capstone.cartservice.entity.Product;
import com.capstone.cartservice.repository.MysqlCartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    MysqlCartRepository cartRepository;

    @Autowired
    CartServiceImpl(MysqlCartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts() {
        return this.cartRepository.findAll();
    }

    public Cart getCartById(UUID cartId) {
        Optional<Cart> op = this.cartRepository.findById(cartId);
        return op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + cartId + " not found in the system"));
    }

    public Cart getCartByUserId(UUID userId) {
        return this.cartRepository.findCartByUserId(userId);
    }

    public Cart createCart(CartRequest cartRequest) {
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

    public void addProductToTheCart(ProductRequest productRequest, UUID userId) {
        Product product = new Product(productRequest);
        Cart cart = cartRepository.findCartByUserId(userId);
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    public void deleteProductFromTheCart(ProductRequest productRequest, UUID userId) throws Exception {
        Cart cart = cartRepository.findCartByUserId(userId);
        List<Product> products = cart.getProducts();
        int index = -1;
        for(int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if(product.getId().equals(productRequest.getId())) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            throw new Exception("Product doesn't exist");
        }
        products.remove(index);
        cart.setProducts(products);
        cartRepository.save(cart);
    }
}
