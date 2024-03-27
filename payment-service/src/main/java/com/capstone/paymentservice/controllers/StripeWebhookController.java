package com.capstone.paymentservice.controllers;

import com.capstone.paymentservice.services.WebhookService;
import com.stripe.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stripeWebhook")
public class StripeWebhookController {

    WebhookService webhookService;

    @Autowired
    public StripeWebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void receiveStatus(@RequestBody Event event) {
        this.webhookService.receiveStatus(event);
    }
}
