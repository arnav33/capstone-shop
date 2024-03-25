package com.capstone.cartservice.dto;

import com.capstone.cartservice.paymentmethods.PaymentMethod;
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
    PaymentMethod paymentMethod;
    UUID addressID;
}
