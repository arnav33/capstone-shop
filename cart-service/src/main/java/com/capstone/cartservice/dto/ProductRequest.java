package com.capstone.cartservice.dto;

import com.capstone.cartservice.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest extends BaseEntity {
    private String productId;
    private float price;
    private int discount;
    private int discountCap;
    private int quantity;
    private String imageUrl;
}
