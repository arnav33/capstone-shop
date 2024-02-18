package com.capstone.productcatalogservice.elastic_model;

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
@Document(indexName = "product_tag")
public class EProductTag {
    Long id;
    String name;
    @UpdateTimestamp
    private Date modificationDate;
}
