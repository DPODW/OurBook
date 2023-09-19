package com.ourbook.shop.service.paymentService;

import com.ourbook.shop.dto.PayMent.PaymentInfo;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    void paymentInfoSave(PaymentInfo paymentInfo);

    void paymentValidate(String orderNumber);
}
