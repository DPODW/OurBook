package com.ourbook.shop.service.paymentService.impl;

import com.ourbook.shop.config.exception.PaymentFailException;
import com.ourbook.shop.dto.payment.KGPaymentCancel;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.mapper.paymentMapper.PaymentMapper;
import com.ourbook.shop.service.paymentService.KGPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class KGPaymentServiceImpl implements KGPaymentService {

    private final PaymentMapper paymentMapper;





    public KGPaymentServiceImpl(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentInfo paymentInfoSave(PaymentInfo paymentInfo,String imp_key,String imp_secret) {
       try {
           orderNumberValidate(paymentInfo.getOrderNumber());
           paymentMapper.paymentInfoSave(paymentInfo);
       }catch (Exception ex){
           String accessToken = getIamportAccessToken(imp_key, imp_secret);
           int paymentPrice = paymentInfo.getPaymentPrice().intValue();
           KGPaymentCancel kgPaymentCancel = new KGPaymentCancel(accessToken,paymentInfo.getPaymentNumber(),"결제 오류 취소",paymentPrice);
           paymentCancel(kgPaymentCancel);
           throw new PaymentFailException("KG 이니시스 결제 정보 저장 실패");
       }
       return paymentInfo;
    }


    @Override
    public void orderNumberValidate(String orderNumber) {
        PaymentInfo paymentHistory = paymentMapper.findOrderNumber(orderNumber);
        if(paymentHistory != null){
            throw new DuplicateKeyException("중복된 주문 번호가 존재합니다.");
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
    public ResponseEntity<String> paymentCancel(KGPaymentCancel KGPaymentCancel){
        String apiUrl = "https://api.iamport.kr/payments/cancel";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        headers.set("Authorization", KGPaymentCancel.getAccessToken());


        String requestBody = "{\"reason\":\"" + KGPaymentCancel.getReason() + "\",\"imp_uid\":\"" + KGPaymentCancel.getImp_uid() +
                "\",\"amount\":" + KGPaymentCancel.getPaymentPrice() + ",\"checksum\":" + KGPaymentCancel.getPaymentPrice() + "}";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
        log.info("{}",exchange);
        return exchange;
    }

}