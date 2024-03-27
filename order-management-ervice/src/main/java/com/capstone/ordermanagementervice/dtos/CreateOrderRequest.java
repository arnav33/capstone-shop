package com.capstone.ordermanagementervice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
public class CreateOrderRequest {
    private UUID userId;
    private UUID addressId;
    private UUID cartId;
}
