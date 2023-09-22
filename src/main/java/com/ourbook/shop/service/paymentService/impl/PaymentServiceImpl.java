package com.ourbook.shop.service.paymentService.impl;

import com.google.gson.JsonObject;
import com.ourbook.shop.config.exception.PaymentFailException;
import com.ourbook.shop.dto.PayMent.PaymentCancel;
import com.ourbook.shop.dto.PayMent.PaymentInfo;
import com.ourbook.shop.mapper.paymentMapper.PaymentMapper;
import com.ourbook.shop.mapper.shopMapper.FindBookMapper;
import com.ourbook.shop.service.paymentService.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;



    public PaymentServiceImpl(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    @Override
    public void paymentInfoSave(PaymentInfo paymentInfo) {
       try {

           paymentMapper.paymentInfoSave(paymentInfo);
       }catch (Exception ex){
           throw new PaymentFailException();
       }
    }

    @Override
    public void paymentValidate(String orderNumber) {
        PaymentInfo paymentHistory = paymentMapper.findOrderNumber(orderNumber);
        if(paymentHistory != null){
            throw new PaymentFailException();
        }else{
            log.info("결제 검증 완료");
        }
    }

    @Override
    public String getIamportAccessToken (String imp_key,String imp_secret) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = "https://api.iamport.kr/users/getToken";

        Map<String, String> iamportKey = new HashMap();
        iamportKey.put("imp_key", imp_key);
        iamportKey.put("imp_secret",imp_secret);

        ResponseEntity<Object> responseData = restTemplate.postForEntity(requestUrl, iamportKey, Object.class);
        LinkedHashMap responseBody = (LinkedHashMap) responseData.getBody();
        LinkedHashMap responseBodyProps = (LinkedHashMap) responseBody.get("response");
        String accessToken = (String) responseBodyProps.get("access_token");
        log.info("아임포트 토큰 : {}",accessToken);
        return accessToken;
    }

    @Override
    public void paymentCancel(PaymentCancel paymentCancel){
        String apiUrl = "https://api.iamport.kr/payments/cancel";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        headers.set("Authorization", paymentCancel.getAccessToken());


        String requestBody = "{\"reason\":\"" + paymentCancel.getReason() + "\",\"imp_uid\":\"" + paymentCancel.getImp_uid() +
                "\",\"amount\":" + paymentCancel.getPaymentPrice() + ",\"checksum\":" + paymentCancel.getPaymentPrice() + "}";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
    }

}
