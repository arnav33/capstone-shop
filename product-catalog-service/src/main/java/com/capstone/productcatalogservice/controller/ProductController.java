package com.capstone.productcatalogservice.controller;

import com.capstone.productcatalogservice.dto.ProductRequest;
import com.capstone.productcatalogservice.dto.ProductResponse;
import com.capstone.productcatalogservice.model.Tag;
import com.capstone.productcatalogservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable String productId) {
        return this.productService.getProductById(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return this.productService.createProduct(productRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody ProductRequest productRequest) {
        this.productService.updateProduct(productRequest);
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable String productId) {
        this.productService.deleteProduct(productId);
    }

    @PostMapping("/search")
    public List<ProductResponse> search(@RequestBody Tag tag) {
        return this.productService.search(tag);
    }
}
