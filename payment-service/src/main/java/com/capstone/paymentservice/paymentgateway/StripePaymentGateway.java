package com.capstone.paymentservice.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.WebhookEndpointCreateParams;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StripePaymentGateway implements PaymentGateway {

    @Override
    public Map<String, String> getPaymentLink(String name, long amount, String currency, int quantity) throws StripeException {
        Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";

//        String productId = this.createProduct((name)).getId();
//
//        Price price = this.createPrice(productId, currency, amount);
//
//
//        PaymentLinkCreateParams params =
//                PaymentLinkCreateParams.builder()
//                        .addLineItem(
//                                PaymentLinkCreateParams.LineItem.builder()
//                                        .setPrice(price.getId())
//                                        .setQuantity((long) quantity)
//                                        .build()
//                        )
//                        .setAfterCompletion(
//                                PaymentLinkCreateParams.AfterCompletion.builder()
//                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.HOSTED_CONFIRMATION)
//                                        .setRedirect(
//                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
//                                                        .setUrl("http://localhost:4242/stripe_webhooks/api/stripeWebhook")
//                                                        .build()
//                                        )
//                                        .build()
//                        )
//                        .build();
//
//        PaymentLink paymentLink = PaymentLink.create(params);





        Map<String, Object> productParams = new HashMap<>();
        productParams.put("name", name);

        Map<String, Object> priceParams = new HashMap<>();
        priceParams.put("unit_amount", amount);
        priceParams.put("currency", currency);
        priceParams.put("product_data", productParams);

        Price price = Price.create(priceParams);

        Map<String, Object> lineItem = new HashMap<>();
        lineItem.put("price", price.getId());
        lineItem.put("quantity", quantity);

        List<Object> lineItems = new ArrayList<>();
        lineItems.add(lineItem);

        Map<String, String> redirect = new HashMap<>();
        redirect.put("url", "http://localhost:4242/ui/success-page");
        Map<String, Object> afterCompletion = new HashMap<>();
        afterCompletion.put("type", "redirect");
        afterCompletion.put("redirect", redirect);

        Map<String, Object> params = new HashMap<>();
        params.put("line_items", lineItems);
        params.put("after_completion", afterCompletion);

        PaymentLink paymentLink = PaymentLink.create(params);

        Map<String, String> response = new HashMap<>();
        response.put("url", paymentLink.getUrl());
        response.put("price_id", price.getId());
        return response;
    }
}
