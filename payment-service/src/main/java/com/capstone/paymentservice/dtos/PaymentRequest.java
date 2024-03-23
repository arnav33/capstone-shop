package com.capstone.paymentservice.dtos;

import com.capstone.paymentservice.enumerations.Currency;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String id;
    private String name;
    private float price;
    private int quantity;
    private Currency currency;
}
