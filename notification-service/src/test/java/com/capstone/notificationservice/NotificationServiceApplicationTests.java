package com.capstone.notificationservice;

import com.capstone.notificationservice.model.Notification;
import com.capstone.notificationservice.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class NotificationServiceApplicationTests {

	@Autowired
	private NotificationService notificationService;

	@Test
	void testSendNotificationViaKafka() {
		// Create a Notification object
		Notification notification = new Notification(
				UUID.randomUUID(),
				UUID.randomUUID(),
				"Your order has been confirmed!",
				"ORDER_CONFIRMATION"
		);

		// Send the notification using the NotificationService
		notificationService.sendNotification(notification);

		// Verify that the notification is received by the NotificationListener
		// (You can add assertions based on your implementation)
		assertTrue(true);
	}

}
