package com.capstone.notificationservice.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class NotificationSerializer implements Serializer<Notification> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Notification notification) {
        if (notification == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(notification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing Notification", e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No specific configuration needed for this serializer
    }

    @Override
    public void close() {
        // Cleanup if needed
    }
}
