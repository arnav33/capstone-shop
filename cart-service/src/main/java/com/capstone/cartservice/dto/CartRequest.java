package com.capstone.cartservice.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    UUID userId;
}
