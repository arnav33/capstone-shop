package com.capstone.cartservice;

import com.capstone.cartservice.controller.CartController;
import com.capstone.cartservice.dto.CartRequest;
import com.capstone.cartservice.dto.CartResponse;
import com.capstone.cartservice.dto.ProductRequest;
import com.capstone.cartservice.entity.Cart;
import com.capstone.cartservice.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartControllerTest {

	@InjectMocks
	private CartController cartController;

	@Mock
	private CartService cartService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllCarts() {
		// Arrange
		List<Cart> carts = Arrays.asList(new Cart(), new Cart());
		when(cartService.getAllCarts()).thenReturn(carts);

		// Act
		List<CartResponse> result = cartController.getAllCarts();

		// Assert
		assertEquals(2, result.size());
		verify(cartService, times(1)).getAllCarts();
	}

	@Test
	void testGetCartById() {
		// Arrange
		UUID cartId = UUID.randomUUID();
		Cart cart = new Cart();
		when(cartService.getCartById(cartId)).thenReturn(cart);

		// Act
		CartResponse result = cartController.getCartById(cartId);

		// Assert
		assertNotNull(result);
		verify(cartService, times(1)).getCartById(cartId);
	}

	@Test
	void testGetCartByUserId() {
		// Arrange
		UUID userId = UUID.randomUUID();
		Cart cart = new Cart();
		when(cartService.getCartByUserId(userId)).thenReturn(cart);

		// Act
		CartResponse result = cartController.getCartByUserId(userId);

		// Assert
		assertNotNull(result);
		verify(cartService, times(1)).getCartByUserId(userId);
	}

	@Test
	void testCreateCart() {
		// Arrange
		CartRequest cartRequest = new CartRequest();
		Cart createdCart = new Cart();
		when(cartService.createCart(cartRequest)).thenReturn(createdCart);

		// Act
		CartResponse result = cartController.createCart(cartRequest);

		// Assert
		assertNotNull(result);
		verify(cartService, times(1)).createCart(cartRequest);
	}

	@Test
	void testUpdateProduct() {
		// Arrange
		UUID cartId = UUID.randomUUID();
		CartRequest cartRequest = new CartRequest();
		doNothing().when(cartService).updateProduct(cartRequest, cartId);

		// Act
		cartController.updateProduct(cartRequest, cartId);

		// Assert
		verify(cartService, times(1)).updateProduct(cartRequest, cartId);
	}

	@Test
	void testAddProductToTheCart() {
		// Arrange
		ProductRequest productRequest = new ProductRequest();
		doNothing().when(cartService).addProductToTheCart(productRequest);

		// Act
		cartController.addProductToTheCart(productRequest);

		// Assert
		verify(cartService, times(1)).addProductToTheCart(productRequest);
	}

	@Test
	void testDeleteProductFromTheCart() throws Exception {
		// Arrange
		UUID userId = UUID.randomUUID();
		ProductRequest productRequest = new ProductRequest();
		doNothing().when(cartService).deleteProductFromTheCart(productRequest, userId);

		// Act
		cartController.deleteProductFromTheCart(productRequest, userId);

		// Assert
		verify(cartService, times(1)).deleteProductFromTheCart(productRequest, userId);
	}
}
