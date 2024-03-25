package com.capstone.cartservice.entity;

import com.capstone.cartservice.dto.ProductRequest;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity {

    private float price;
    private int discount;
    private int discountCap;
    private int quantity;
    private String imageUrl;

    public Product(ProductRequest productRequest) {
        this.price = productRequest.getPrice();
        this.discount = productRequest.getDiscount();
        this.discountCap = productRequest.getDiscountCap();
        this.quantity = productRequest.getQuantity();
        this.imageUrl = productRequest.getImageUrl();
    }
}
