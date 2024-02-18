package com.capstone.productcatalogservice.dto;

import com.capstone.productcatalogservice.enumeration.Category;
import com.capstone.productcatalogservice.entity.ProductAttribute;
import com.capstone.productcatalogservice.entity.ProductReview;
import com.capstone.productcatalogservice.entity.ProductTag;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductRequest {
    private String name;
    private String description;
    private Category category;
    private float price;
    private int discount;
    private int discountCap;                    // max discount amount. 0 if not applicable
    private int quantity;
    private String imageUrl;
    private List<ProductTag> productTags;                     // for searching
    private List<ProductReview> productReviews;
    private List<ProductAttribute> productAttributes;         // for product comparison
}
