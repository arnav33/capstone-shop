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
@Document(indexName = "product_attribute")
public class EProductAttribute {
    Long id;
    String attributeKey;
    String attributeValue;
    @UpdateTimestamp
    private Date modificationDate;
}
