package com.ourbook.shop.service.paymentService;

import com.ourbook.shop.dto.PayMent.PaymentCancel;
import com.ourbook.shop.dto.PayMent.PaymentInfo;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public interface PaymentService {
    void paymentInfoSave(PaymentInfo paymentInfo);

    void paymentValidate(String orderNumber);

    void paymentCancel(PaymentCancel paymentCancel);

    String getIamportAccessToken(String imp_key,String imp_secret);


}
