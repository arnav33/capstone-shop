package com.capstone.paymentservice.services;

import com.capstone.paymentservice.dtos.PaymentRequest;
import com.capstone.paymentservice.entities.ProductReceipt;
import com.capstone.paymentservice.enumerations.PaymentStatus;
import com.capstone.paymentservice.paymentgateway.PaymentGateway;
import com.capstone.paymentservice.repositories.MysqlProductReceiptRepository;
import com.stripe.exception.StripeException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    PaymentGateway paymentGateway;

    MysqlProductReceiptRepository productReceiptRepository;

    @Autowired
    public PaymentServiceImpl(PaymentGateway paymentGateway, MysqlProductReceiptRepository productReceiptRepository) {
        this.paymentGateway = paymentGateway;
        this.productReceiptRepository = productReceiptRepository;
    }

    public Map<String, String> initiatePayment(PaymentRequest paymentRequest) throws StripeException {
        Map<String, String> paymentLink = this.paymentGateway.getPaymentLink(paymentRequest.getName(), (long) paymentRequest.getPrice() * 100,
                paymentRequest.getCurrency().name(), paymentRequest.getQuantity());
        String priceId = paymentLink.get("price_id");
        String url = paymentLink.get("url");
        ProductReceipt productReceipt = new ProductReceipt();
        productReceipt.setName(paymentRequest.getName());
        productReceipt.setPrice(paymentRequest.getPrice());
        productReceipt.setQuantity(paymentRequest.getQuantity());
        productReceipt.setProductId(paymentRequest.getId());
        productReceipt.setPriceId(priceId);
        productReceipt.setPaymentStatus(PaymentStatus.PENDING);
        ProductReceipt receipt = this.productReceiptRepository.save(productReceipt);
        Map<String, String> response = new HashMap<>();
        response.put("transaction_id", receipt.getId().toString());
        response.put("url", url);
        return response;
    }

    public Object getReceipt(UUID id) {
        Optional<ProductReceipt> productReceipt =  this.productReceiptRepository.findById(id);
        return productReceipt.orElse(null);
    }

    public void updatePaymentStatus(PaymentStatus status) {

    }
}
