package com.capstone.cartservice.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String productId;
    private float price;
    private int discount;
    private int discountCap;
    private int quantity;
    private String imageUrl;
}
