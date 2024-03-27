package com.capstone.cartservice.dto;

import com.capstone.cartservice.entity.BaseEntity;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest extends BaseEntity {
    private UUID cartId;
    private float price;
    private int discount;
    private int discountCap;
    private int quantity;
    private String imageUrl;
}
