package com.capstone.cartservice.dto;

import com.capstone.cartservice.enumeration.PayType;
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
public class PurchaseRequest {
    private UUID userId;
    private UUID addressId;
    private UUID cartId;
    private PayType payType;
}
