package com.capstone.productcatalogservice.elastic_model;

import com.capstone.productcatalogservice.enumeration.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "product_review")
public class EProductReview {
    Long id;
    String comment;
    Rating rating;
    @UpdateTimestamp
    private Date modificationDate;
}
