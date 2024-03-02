package com.capstone.cartservice.entity;

import com.capstone.cartservice.dto.ProductRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    private String productId;
    private float price;
    private int discount;
    private int discountCap;
    private int quantity;
    private String imageUrl;

    public Product(ProductRequest productRequest) {
        this.productId = productRequest.getProductId();
        this.price = productRequest.getPrice();
        this.discount = productRequest.getDiscount();
        this.discountCap = productRequest.getDiscountCap();
        this.quantity = productRequest.getQuantity();
        this.imageUrl = productRequest.getImageUrl();
    }
}
