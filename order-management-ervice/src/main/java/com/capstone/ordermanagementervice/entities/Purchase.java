package com.capstone.ordermanagementervice.entities;

import com.capstone.ordermanagementervice.dtos.PurchaseRequest;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
