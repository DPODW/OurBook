package com.ourbook.shop.service.paymentService;

import com.ourbook.shop.dto.payment.KGPaymentCancel;
import com.ourbook.shop.dto.payment.PaymentInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface KGPaymentService {
    PaymentInfo paymentInfoSave(PaymentInfo paymentInfo, String imp_key, String imp_secret);

    void orderNumberValidate(String orderNumber);

    ResponseEntity<String> paymentCancel(KGPaymentCancel KGPaymentCancel);

    String getIamportAccessToken(String imp_key,String imp_secret);


}
