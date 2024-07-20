package com.capstone.notificationservice.listener;

import com.capstone.notificationservice.model.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @KafkaListener(topics = "notifications", groupId = "notification-group")
    public void listen(Notification notification) {
        // Process the notification (e.g., send SMS, log, etc.)
        System.out.println("Received notification: " + notification);
    }
}
