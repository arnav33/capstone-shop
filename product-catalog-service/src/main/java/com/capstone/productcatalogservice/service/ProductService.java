package com.capstone.productcatalogservice.service;

import com.capstone.productcatalogservice.dto.ProductRequest;
import com.capstone.productcatalogservice.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long productId);
    Product createProduct(ProductRequest productRequest);
    void updateProduct(ProductRequest productRequest, Long productId);
    void deleteProduct(Long id);
}
