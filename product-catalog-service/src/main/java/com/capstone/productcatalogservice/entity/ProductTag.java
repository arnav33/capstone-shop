package com.capstone.productcatalogservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProductTag extends BaseEntity {

    String name;
}
