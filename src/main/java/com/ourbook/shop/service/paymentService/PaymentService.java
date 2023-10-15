package com.ourbook.shop.service.paymentService;

import com.ourbook.shop.dto.payment.PaymentInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {

    List<PaymentInfo> findPaymentHistory(String email);

    String findPaymentResultImg(String bookId);

    List<String> findPaymentHistoryImg(List<PaymentInfo> paymentHistory);

    PaymentInfo checkPaymentNull(PaymentInfo paymentInfo);

    void orderNumberValidate(String orderNumber);
}
