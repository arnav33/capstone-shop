package com.capstone.paymentservice.paymentgateway;

import java.util.Map;

import com.stripe.exception.StripeException;

public interface PaymentGateway {

    Map<String, String> getPaymentLink(String name, long price, String currency, int quantity) throws StripeException;
}
