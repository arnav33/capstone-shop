package com.capstone.paymentservice.entities;

import java.util.UUID;

import com.capstone.paymentservice.enumerations.Currency;
import com.capstone.paymentservice.enumerations.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProductReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private float price;
    private int quantity;
    private PaymentStatus paymentStatus;
    private String productId;
    private String priceId;
    Currency currency;
}
