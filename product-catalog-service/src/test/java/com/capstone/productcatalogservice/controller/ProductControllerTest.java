package com.capstone.productcatalogservice.controller;

import com.capstone.productcatalogservice.dto.ProductRequest;
import com.capstone.productcatalogservice.dto.ProductResponse;
import com.capstone.productcatalogservice.entity.Product;
import com.capstone.productcatalogservice.service.ElasticSearchService;
import com.capstone.productcatalogservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private ElasticSearchService elasticSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.getAllProducts()).thenReturn(products);

        // Act
        List<ProductResponse> result = productController.getAllProducts();

        // Assert
        assertEquals(2, result.size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(productService.getProductById(productId)).thenReturn(product);

        // Act
        ProductResponse result = productController.getProductById(productId);

        // Assert
        assertNotNull(result);
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void testCreateProduct() throws URISyntaxException, IOException, InterruptedException {
        // Arrange
        ProductRequest productRequest = new ProductRequest();
        Product product = new Product();
        when(productService.createProduct(any(ProductRequest.class))).thenReturn(product);

        // Act
        ProductResponse result = productController.createProduct(productRequest);

        // Assert
        assertNotNull(result);
        verify(productService, times(1)).createProduct(productRequest);
    }

    @Test
    void
