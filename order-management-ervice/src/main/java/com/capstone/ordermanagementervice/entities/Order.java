package com.capstone.ordermanagementervice.entities;

import com.capstone.ordermanagementervice.dtos.OrderRequest;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity {

    private UUID userId;
    private UUID addressId;
    private UUID cartId;
    private List<UUID> transactionIds;

    public Order(OrderRequest orderRequest) {
        this.userId = orderRequest.getUserId();
        this.addressId = orderRequest.getAddressId();
        this.cartId = orderRequest.getCartId();
    }
}
