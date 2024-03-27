package com.capstone.usermanagementservice.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address extends BaseEntity {
    private String flatNo;
    private String street;
    private String city;
    private String pinCode;
    private String state;
}
