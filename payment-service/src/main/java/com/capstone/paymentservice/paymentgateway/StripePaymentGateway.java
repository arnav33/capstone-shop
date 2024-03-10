package com.capstone.paymentservice.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripePaymentGateway implements PaymentGateway {

    @Override
    public Object getPaymentLink() throws StripeException {
        Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";

        Map<String, Object> productParams = new HashMap<>();
        productParams.put("name", "Mobile");

        Map<String, Object> priceParams = new HashMap<>();
        priceParams.put("unit_amount", 1000L);
        priceParams.put("currency", "INR");
        priceParams.put("product_data", productParams);

        Price price = Price.create(priceParams);

        Map<String, Object> lineItem = new HashMap<>();
        lineItem.put("price", price.getId());
        lineItem.put("quantity", 1);

        List<Object> lineItems = new ArrayList<>();
        lineItems.add(lineItem);

        Map<String, Object> params = new HashMap<>();
        params.put("line_items", lineItems);

        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.toJson();
    }
}
