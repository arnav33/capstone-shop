package com.capstone.productcatalogservice.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private int id;
    private String name;
    private String description;
    private String category;
    private float price;
    private int discount;                       // percentage
    private int discountCap;                    // max discount amount. 0 if not applicable
    private int quantity;
    private List<String> images;
    private List<Tag> tags;                     // for searching
    private List<Review> reviews;
    private List<Attribute> attributes;         // for product comparison

}
