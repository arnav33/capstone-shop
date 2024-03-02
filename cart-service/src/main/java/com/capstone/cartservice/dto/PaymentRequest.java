package com.capstone.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
public class PaymentRequest {
    UUID purchaseId;
    IPaymentMethod paymentMethod;
    UUID addressID;
}
