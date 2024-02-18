package com.capstone.productcatalogservice.dto;

import com.capstone.productcatalogservice.enumeration.Category;
import com.capstone.productcatalogservice.entity.Product;
import com.capstone.productcatalogservice.entity.ProductAttribute;
import com.capstone.productcatalogservice.entity.ProductReview;
import com.capstone.productcatalogservice.entity.ProductTag;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private float price;
    private int discount;
    private int discountCap;                                    // max discount amount. 0 if not applicable
    private int quantity;
    private String imageUrl;
    private List<ProductTag> productTags;                       // for searching
    private List<ProductReview> productReviews;
    private List<ProductAttribute> productAttributes;           // for product comparison

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.discountCap = product.getDiscountCap();
        this.quantity = product.getQuantity();
        this.imageUrl = product.getImageUrl();
        this.productTags = product.getProductTags();
        this.productReviews = product.getProductReviews();
        this.productAttributes = product.getProductAttributes();
    }
}
