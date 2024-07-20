package com.capstone.notificationservice.service;

import com.capstone.notificationservice.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private KafkaTemplate<String, Notification> kafkaTemplate;

    @Autowired
    private JavaMailSender emailSender;

    public void sendNotification(Notification notification) {
        // Send notification to Kafka
        kafkaTemplate.send("notifications", notification);

        // Send email (example for email notifications)
        if ("ORDER_CONFIRMATION".equals(notification.getType())) {
            sendEmail(notification);
        }
    }

    private void sendEmail(Notification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("user@example.com"); // Replace with actual user email
        message.setSubject("Order Confirmation");
        message.setText(notification.getMessage());
        emailSender.send(message);
    }
}
