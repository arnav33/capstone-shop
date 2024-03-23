package com.capstone.paymentservice.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StripePaymentGateway implements PaymentGateway {

    @Value("${stripe_api_key}")
    private String apiKey;

    @Override
    public Map<String, String> getPaymentLink(String name, long amount, String currency, int quantity) throws StripeException {
        Stripe.apiKey = apiKey;

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
