package com.capstone.paymentservice.services;

import com.stripe.model.Event;

public interface WebhookService {

    void receiveStatus(Event event);
}
