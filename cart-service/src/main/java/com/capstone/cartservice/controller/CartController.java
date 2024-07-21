package com.capstone.cartservice.controller;

import com.capstone.cartservice.dto.CartRequest;
import com.capstone.cartservice.dto.CartResponse;
import com.capstone.cartservice.dto.ProductRequest;
import com.capstone.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    CartService cartService;

    @Autowired
    CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<CartResponse> getAllCarts() {
        return this.cartService.getAllCarts().stream().map(CartResponse::new).toList();
    }
    @GetMapping("{cartId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CartResponse getCartById(@PathVariable UUID cartId) {
        return new CartResponse(this.cartService.getCartById(cartId));
    }
    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public CartResponse getCartByUserId(@PathVariable UUID userId) {
        return new CartResponse(this.cartService.getCartByUserId(userId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public CartResponse createCart(@RequestBody CartRequest cartRequest) {
        return new CartResponse(this.cartService.createCart(cartRequest));
    }
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public void updateProduct(@RequestBody CartRequest cartRequest, UUID cartId) {
        this.cartService.updateProduct(cartRequest, cartId);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public void addProductToTheCart(@RequestBody ProductRequest productRequest) {
        this.cartService.addProductToTheCart(productRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public void deleteProductFromTheCart(@RequestBody ProductRequest productRequest, UUID userId) throws Exception {
        this.cartService.deleteProductFromTheCart(productRequest, userId);
    }
}
