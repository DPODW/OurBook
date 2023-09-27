package com.ourbook.shop.service.paymentService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TossPaymentService {

    ResponseEntity<String> paymentValidate(String orderId, String paymentKey, String amount);
}
