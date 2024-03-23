package com.capstone.paymentservice.services;

import com.capstone.paymentservice.dtos.PaymentRequest;
import com.capstone.paymentservice.enumerations.PaymentStatus;
import com.stripe.exception.StripeException;

import java.util.Map;
import java.util.UUID;

public interface PaymentService {

    Map<String, String> initiatePayment(PaymentRequest paymentRequest) throws StripeException;
    Object getReceipt(UUID id);
    void updatePaymentStatus(PaymentStatus status);
}
