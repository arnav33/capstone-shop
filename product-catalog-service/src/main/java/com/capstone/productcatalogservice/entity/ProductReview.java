package com.capstone.productcatalogservice.entity;

import com.capstone.productcatalogservice.enumeration.Rating;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProductReview extends BaseEntity {

    String comment;

    Rating rating;
}
