package com.capstone.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private UUID id;
    private UUID userId;
    private String message;
    private String type;
}
