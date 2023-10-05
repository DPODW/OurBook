package com.ourbook.shop.service.paymentService;

import com.ourbook.shop.dto.payment.KGPaymentCancel;
import com.ourbook.shop.dto.payment.PaymentInfo;
import org.springframework.stereotype.Service;

@Service
public interface KGPaymentService {
    void paymentInfoSave(PaymentInfo paymentInfo);

    void paymentValidate(String orderNumber);

    void paymentCancel(KGPaymentCancel KGPaymentCancel);

    String getIamportAccessToken(String imp_key,String imp_secret);


}
