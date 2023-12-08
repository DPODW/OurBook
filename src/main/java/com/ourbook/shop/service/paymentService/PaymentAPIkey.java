package com.ourbook.shop.service.paymentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentAPIkey {

    /**
     * 결제 Secret Key 를 properties 에서 가져오는 클래스.
     * 결제 Class 에서 결제 Secret Key 를 properties 에서 바로 가져오게 되면,
     * 생성자 호출 시점 문제로 인하여 Null 이 반환된다. (컨트롤러 호출 -> VALUE 값 끌어오기 순서이기 때문에)
     *
     * 그렇기 때문에 결제 Secret Key 를 가져오는 별도의 클래스가 필요하다.
     * **/

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
