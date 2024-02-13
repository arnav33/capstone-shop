package com.capstone.productcatalogservice.dto;

import com.capstone.productcatalogservice.model.Attribute;
import com.capstone.productcatalogservice.model.Review;
import com.capstone.productcatalogservice.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private int id;
    private String name;
    private String description;
    private String category;
    private float price;
    private int discount;
    private int discountCap;                    // max discount amount. 0 if not applicable
    private int quantity;
    private List<String> images;
    private List<Tag> tags;                     // for searching
    private List<Review> reviews;
    private List<Attribute> attributes;         // for product comparison

}
