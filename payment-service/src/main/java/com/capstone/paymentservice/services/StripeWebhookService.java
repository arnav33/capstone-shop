package com.capstone.paymentservice.services;

import com.capstone.paymentservice.enumerations.PaymentStatus;
import com.stripe.model.Event;
import org.springframework.stereotype.Service;

@Service
public class StripeWebhookService {

    PaymentService paymentService;

    public StripeWebhookService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void receiveStatus(Event event) {

        System.out.println("event type: " + event.toJson());

        switch (event.getType()) {
            case "payment_link.created": break;
            case "payment_link.updated": break;
            case "payment_intent.cancelled": break;
            case "payment_intent.created":
                System.out.println("payment created");
                this.paymentService.updatePaymentStatus(PaymentStatus.SUCCESSFUL);
                break;
            case "payment_intent.payment_failed":
                System.out.println("payment failed");
                this.paymentService.updatePaymentStatus(PaymentStatus.FAILED);
                break;
            case "payment_intent.processing": break;
            case "payment_intent.requires_action": break;
            case "payment_intent.succeeded":
                System.out.println("payment succeeded");
                this.paymentService.updatePaymentStatus(PaymentStatus.SUCCESSFUL);
                break;
            case "payment_method.attached": break;
            case "payment_method.automatically_updated": break;
            case "payment_method.detached": break;
            case "payment_method.updated": break;
            case "payout.canceled": break;
            case "payout.created": break;
            case "payout.failed": break;
            case "payout.paid": break;
            case "payout.reconciliation_completed": break;
            case "payout.updated": break;
            case "person.created": break;
            case "person.deleted": break;
            case "person.updated": break;
            case "price.created": break;
            case "price.deleted": break;
            case "price.updated": break;
            case "product.created": break;
            case "product.deleted": break;
            case "product.updated": break;
            case "application.application.authorized": break;
            case "application.application.deauthorized": break;
            case "account.external_account.created": break;
            case "account.external_account.deleted": break;
            case "account.external_account.updated": break;
            case "account.updated": break;
            case "balance.available": break;
            case "billing_portal.configuration.created": break;
            case "billing_portal.configuration.updated": break;
            case "billing_portal.session.created": break;
            case "cash_balance.funds_available": break;
            case "charge.captured": break;
            case "charge.succeeded":
                System.out.println("charge succeeded!");
                break;
            case "charge.dispute.closed": break;
            case "charge.dispute.created": break;
            case "charge.dispute.funds_reinstated": break;
            case "charge.dispute.funds_withdrawn": break;
            case "charge.dispute.updated": break;
        }
    }
}
