package com.capstone.productcatalogservice.dto;

import com.capstone.productcatalogservice.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SearchRequest {
    Product product;
    String[] fields;
}
