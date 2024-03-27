package com.capstone.ordermanagementervice.entities;

import com.capstone.ordermanagementervice.dtos.CreateOrderRequest;
import com.capstone.ordermanagementervice.dtos.OrderRequest;
import com.capstone.ordermanagementervice.enumerations.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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
public class ProductOrder extends BaseEntity {

    private UUID userId;
    private UUID addressId;
    private UUID cartId;
    @Enumerated
    private PaymentStatus paymentStatus;

    public ProductOrder(OrderRequest orderRequest) {
        this.userId = orderRequest.getUserId();
        this.addressId = orderRequest.getAddressId();
        this.cartId = orderRequest.getCartId();
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public ProductOrder(CreateOrderRequest createOrderRequest) {
        this.userId = createOrderRequest.getUserId();
        this.addressId = createOrderRequest.getAddressId();
        this.cartId = createOrderRequest.getCartId();
        this.paymentStatus = PaymentStatus.PENDING;
    }
}
