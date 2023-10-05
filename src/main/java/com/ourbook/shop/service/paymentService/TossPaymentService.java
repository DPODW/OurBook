package com.ourbook.shop.service.paymentService;

import com.ourbook.shop.dto.payment.PaymentInfo;
import org.springframework.stereotype.Service;

@Service
public interface TossPaymentService {

    void TossPaymentValidate(String orderId, String paymentKey, String amount);

    void TossPaymentCancel(String paymentKey);

    void TossPaymentInfoSave(PaymentInfo paymentInfo,String paymentKey);

    void TossOrderNumberValidate(String orderNumber);
}
