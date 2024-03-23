package com.capstone.productcatalogservice.service;

import com.capstone.productcatalogservice.dto.ProductRequest;
import com.capstone.productcatalogservice.entity.Product;
import com.capstone.productcatalogservice.wrapper.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceInpl implements ProductService {

    ProductRepository productRepository;

    @Autowired
    ProductServiceInpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        Optional<Product> op = this.productRepository.findById(productId);
        return op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + productId + " not found in the system"));
    }

    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product(productRequest);
        product.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        product.getProductAttributes().forEach(productAttribute -> productAttribute.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE));
        product.getProductReviews().forEach(productReview -> productReview.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE));
        product.getProductTags().forEach(productTag -> productTag.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE));
        this.productRepository.save(product);
        return product;
    }

    public void updateProduct(ProductRequest productRequest, Long productId) {
        Product product = new Product(productRequest);
        Optional<Product> op = productRepository.findById(productId);
        Product savedProduct = op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + productId + " not found in the system"));
        product.setId(savedProduct.getId());
        this.productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }
}
