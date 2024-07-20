package com.capstone.ordermanagementervice.dtos;

import com.capstone.ordermanagementervice.enumerations.PayType;
import com.capstone.ordermanagementervice.enumerations.PaymentStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private UUID orderId;
    private UUID userId;
    private UUID addressId;
    private UUID cartId;
    private PayType payType;
    private PaymentStatus paymentStatus;
}
