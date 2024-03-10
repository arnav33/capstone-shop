package com.capstone.paymentservice.paymentgateway;

import com.stripe.exception.StripeException;

public interface PaymentGateway {

    Object getPaymentLink() throws StripeException;
}
