package com.capstone.paymentservice.services;

import com.capstone.paymentservice.paymentgateway.PaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Object initiatePayment() throws StripeException {
        return this.paymentGateway.getPaymentLink();
    }
}
