package com.capstone.cartservice.entity;

import com.capstone.cartservice.dto.PurchaseRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Purchase extends BaseEntity {

    private UUID userId;
    private UUID addressId;
    private UUID cartId;
    private String transactionId;

    public Purchase(PurchaseRequest purchaseRequest) {
        this.userId = purchaseRequest.getUserId();
        this.addressId = purchaseRequest.getAddressId();
        this.cartId = purchaseRequest.getCartId();
    }
}
