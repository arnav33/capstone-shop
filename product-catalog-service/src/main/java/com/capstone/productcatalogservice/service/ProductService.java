package com.capstone.productcatalogservice.service;

import com.capstone.productcatalogservice.dto.ProductRequest;
import com.capstone.productcatalogservice.dto.ProductResponse;
import com.capstone.productcatalogservice.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public List<ProductResponse> getAllProducts() {
        return null;
    }

    public ProductResponse getProductById(String productId) {
        return null;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        return null;
    }

    public void updateProduct(ProductRequest productRequest) {

    }

    public void deleteProduct(String id) {

    }

    public List<ProductResponse> search(Tag tag) {
        return null;
    }
}
