package com.capstone.productcatalogservice.elastic_model;

import com.capstone.productcatalogservice.elastic_model.EProductAttribute;
import com.capstone.productcatalogservice.elastic_model.EProductReview;
import com.capstone.productcatalogservice.elastic_model.EProductTag;
import com.capstone.productcatalogservice.entity.Product;
import com.capstone.productcatalogservice.entity.ProductAttribute;
import com.capstone.productcatalogservice.entity.ProductReview;
import com.capstone.productcatalogservice.entity.ProductTag;
import com.capstone.productcatalogservice.enumeration.Category;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "product")
public class EProduct {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private float price;
    private int discount;
    private int discountCap;
    private int quantity;
    private String imageUrl;
    private List<EProductAttribute> productAttributes;                       // for product comparison
    private List<EProductReview> productReviews;
    private List<EProductTag> productTags;                                   // for searching
    @UpdateTimestamp
    private Date modificationDate;

    public EProduct(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.discountCap = product.getDiscountCap();
        this.quantity = product.getQuantity();
        this.imageUrl = product.getImageUrl();
        List<ProductTag> productTags = product.getProductTags();
        this.productTags = new ArrayList<>();
        for(ProductTag productTag : productTags) {
            EProductTag eProductTag = new EProductTag();
            eProductTag.setId(productTag.getId());
            eProductTag.setName(productTag.getName());
            this.productTags.add(eProductTag);
        }
        List<ProductReview> productReviews = product.getProductReviews();
        this.productReviews = new ArrayList<>();
        for(ProductReview productReview : productReviews) {
            EProductReview eProductReview = new EProductReview();
            eProductReview.setId(productReview.getId());
            eProductReview.setRating(productReview.getRating());
            eProductReview.setComment(productReview.getComment());
            this.productReviews.add(eProductReview);
        }
        List<ProductAttribute> productAttributes = product.getProductAttributes();
        this.productAttributes = new ArrayList<>();
        for(ProductAttribute productAttribute : productAttributes) {
            EProductAttribute eProductAttribute = new EProductAttribute();
            eProductAttribute.setId(productAttribute.getId());
            eProductAttribute.setAttributeKey(productAttribute.getAttributeKey());
            eProductAttribute.setAttributeValue(productAttribute.getAttributeValue());
            this.productAttributes.add(eProductAttribute);
        }
    }
}
