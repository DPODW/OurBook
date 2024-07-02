package com.ourbook.shop.service.paymentService;

import com.ourbook.shop.dto.payment.PaymentInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface PaymentService {

    void paymentInfoDelete(String orderNumber);
    List<PaymentInfo> findPaymentHistory(String email);

    String findPaymentResultImg(String bookId);

    List<String> findPaymentHistoryImg(List<PaymentInfo> paymentHistory);

    PaymentInfo findOrderNumber(String orderNumber);

    PaymentInfo checkPaymentNull(PaymentInfo paymentInfo);

    void orderNumberValidate(String orderNumber);

    void paymentPriceValidate(String bookId,BigDecimal paymentPrice);
}
