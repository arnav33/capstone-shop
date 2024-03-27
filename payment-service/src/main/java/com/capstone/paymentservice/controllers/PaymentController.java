package com.capstone.paymentservice.controllers;

import com.capstone.paymentservice.dtos.PaymentRequest;
import com.capstone.paymentservice.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Object initiatePayment(@RequestBody PaymentRequest paymentRequest) throws StripeException {
        return this.paymentService.initiatePayment(paymentRequest);
    }

    @GetMapping("/{transactionId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object getReceipt(@RequestParam UUID transactionId) {
        return this.paymentService.getReceipt(transactionId);
    }
}
