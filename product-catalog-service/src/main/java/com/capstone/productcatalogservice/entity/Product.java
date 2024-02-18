package com.capstone.productcatalogservice.entity;

import com.capstone.productcatalogservice.dto.ProductRequest;
import com.capstone.productcatalogservice.elastic_model.EProduct;
import com.capstone.productcatalogservice.enumeration.Category;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product extends BaseEntityAudit {
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    private float price;

    private int discount;                                                   // percentage

    private int discountCap;                                                // max discount amount. 0 if not applicable

    private int quantity;

    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<ProductAttribute> productAttributes;                       // for product comparison

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<ProductReview> productReviews;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<ProductTag> productTags;                                   // for searching


    public Product(ProductRequest productRequest) {
        this.name = productRequest.getName();
        this.description = productRequest.getDescription();
        this.category = productRequest.getCategory();
        this.price = productRequest.getPrice();
        this.discount = productRequest.getDiscount();
        this.discountCap = productRequest.getDiscountCap();
        this.quantity = productRequest.getQuantity();
        this.imageUrl = productRequest.getImageUrl();
        this.productTags = productRequest.getProductTags();
        this.productReviews = productRequest.getProductReviews();
        this.productAttributes = productRequest.getProductAttributes();
    }

    public Product(EProduct eProduct) {
        this.setId(eProduct.getId());
        this.name = eProduct.getName();
        this.description = eProduct.getDescription();
        this.category = eProduct.getCategory();
        this.price = eProduct.getPrice();
        this.discount = eProduct.getDiscount();
        this.discountCap = eProduct.getDiscountCap();
        this.quantity = eProduct.getQuantity();
        this.imageUrl = eProduct.getImageUrl();
    }
}
