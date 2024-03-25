package com.capstone.cartservice.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address extends BaseEntity {
    private String flatNo;
    private String street;
    private String city;
    private String pinCode;
    private String state;
}
