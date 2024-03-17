package com.capstone.paymentservice.controllers;

import com.capstone.paymentservice.services.StripeWebhookService;
import com.stripe.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stripeWebhook")
public class StripeWebhookController {

    StripeWebhookService stripeWebhookService;

    @Autowired
    public StripeWebhookController(StripeWebhookService stripeWebhookService) {
        this.stripeWebhookService = stripeWebhookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void receiveStatus(@RequestBody Event event) {
        this.stripeWebhookService.receiveStatus(event);
    }
}
