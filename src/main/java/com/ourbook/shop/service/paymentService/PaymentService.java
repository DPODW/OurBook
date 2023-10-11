package com.ourbook.shop.service.paymentService;

import com.ourbook.shop.dto.payment.PaymentInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {

    List<PaymentInfo> findPaymentHistory(String email);

    List<String> findPaymentImg(List<PaymentInfo> paymentHistory);
}
