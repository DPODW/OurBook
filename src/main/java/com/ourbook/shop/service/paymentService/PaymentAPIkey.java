package com.ourbook.shop.service.paymentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentAPIkey {

    @Value("${iamPort.imp_key}")
    private String IAMPORT_imp_key;


    @Value("${iamPort.imp_secret}")
    private String IAMPORT_imp_secret;

    @Value("${toss.secretKey}")
    private String TOSS_secretKey;



    public String getIAMPORT_imp_key(){
        return IAMPORT_imp_key;
    }

    public String getIAMPORT_imp_secret(){
        return IAMPORT_imp_secret;
    }

    public String getTOSS_secretKey(){
        return TOSS_secretKey;
    }
}
