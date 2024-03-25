package com.capstone.ordermanagementervice.dtos;

import com.capstone.ordermanagementervice.enumerations.PayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
public class OrderRequest {
    private UUID userId;
    private UUID addressId;
    private UUID cartId;
    private PayType payType;
}
