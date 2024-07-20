package com.capstone.paymentservice.controllers;

import com.capstone.paymentservice.dtos.PaymentRequest;
import com.capstone.paymentservice.services.PaymentService;
import com.stripe.exception.StripeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitiatePayment() throws StripeException {
        // Arrange
        PaymentRequest paymentRequest = new PaymentRequest();
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("status", "success");
        when(paymentService.initiatePayment(any(PaymentRequest.class))).thenReturn(expectedResponse);

        // Act
        Object result = paymentController.initiatePayment(paymentRequest);

        // Assert
        assertNotNull(result);
        assertTrue(result instanceof Map);
        assertEquals("success", ((Map<String, String>) result).get("status"));
        verify(paymentService, times(1)).initiatePayment(paymentRequest);
    }

    @Test
    void testInitiatePayment_StripeException() throws StripeException {
        // Arrange
        PaymentRequest paymentRequest = new PaymentRequest();
        when(paymentService.initiatePayment(any(PaymentRequest.class))).thenThrow(new Exception("Payment failed", null));

        // Act & Assert
        Exception exception = assertThrows(StripeException.class, () -> {
            paymentController.initiatePayment(paymentRequest);
        });
        assertEquals("Payment failed", exception.getMessage());
        verify(paymentService, times(1)).initiatePayment(paymentRequest);
    }

    @Test
    void testGetReceipt() {
        // Arrange
        UUID transactionId = UUID.randomUUID();
        Object expectedReceipt = new Object();  // Replace with actual receipt object
        when(paymentService.getReceipt(transactionId)).thenReturn(expectedReceipt);

        // Act
        Object result = paymentController.getReceipt(transactionId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedReceipt, result);
        verify(paymentService, times(1)).getReceipt(transactionId);
    }

    @Test
    void testGetReceipt_NotFound() {
        // Arrange
        UUID transactionId = UUID.randomUUID();
        when(paymentService.getReceipt(transactionId)).thenThrow(new RuntimeException("Receipt not found"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            paymentController.getReceipt(transactionId);
        });
        assertEquals("Receipt not found", exception.getMessage());
        verify(paymentService, times(1)).getReceipt(transactionId);
    }
}
