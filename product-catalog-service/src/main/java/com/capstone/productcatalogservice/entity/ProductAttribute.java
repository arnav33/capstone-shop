package com.capstone.productcatalogservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProductAttribute extends BaseEntity {

    String attributeKey;

    String attributeValue;
}
