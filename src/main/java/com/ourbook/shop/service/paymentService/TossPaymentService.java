package com.ourbook.shop.service.paymentService;

import com.ourbook.shop.dto.PayMent.PaymentInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TossPaymentService {

    void TossPaymentValidate(String orderId, String paymentKey, String amount);

    void TossPaymentInfoSave(PaymentInfo paymentInfo,String paymentKey);
}
