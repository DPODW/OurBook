package com.ourbook.shop.service.paymentService.impl;

import com.ourbook.shop.config.exception.AjaxResponseException;
import com.ourbook.shop.dto.payment.KGPaymentCancel;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.mapper.paymentMapper.PaymentMapper;
import com.ourbook.shop.mapper.shopMapper.BookCartMapper;
import com.ourbook.shop.service.additionService.emailService.EmailService;
import com.ourbook.shop.service.paymentService.KGPaymentService;
import com.ourbook.shop.service.paymentService.PaymentService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
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

    private final PaymentService paymentService;

    private final BookCartMapper bookCartMapper;

    private final EmailService emailService;


    public KGPaymentServiceImpl(PaymentMapper paymentMapper, PaymentService paymentService, BookCartMapper bookCartMapper, EmailService emailService) {
        this.paymentMapper = paymentMapper;
        this.paymentService = paymentService;
        this.bookCartMapper = bookCartMapper;
        this.emailService = emailService;
    }

    @Override
    public PaymentInfo paymentInfoSave(PaymentInfo paymentInfo,String imp_key,String imp_secret) {
       try {
           validateAndSave(paymentInfo);
           return paymentInfo;
       }catch (Exception ex){
           String accessToken = getIamportAccessToken(imp_key, imp_secret);
           KGPaymentCancel kgPaymentCancel = new KGPaymentCancel(accessToken,paymentInfo.getPaymentNumber(),"결제 오류 취소",paymentInfo.getPaymentPrice().setScale(0));
           paymentCancel(kgPaymentCancel);

           if(paymentMapper.findOrderNumber(paymentInfo.getOrderNumber())!=null){
               paymentMapper.paymentInfoDelete(paymentInfo.getOrderNumber()); //결제 내역이 이미 생성되어버렸다면 -> 제거
           }
           log.error("결제 내역 저장 실패. 결제 금액은 즉시 환불 됩니다. 예외 발생 위치=> {}",ex.getStackTrace()[0]);
           throw new AjaxResponseException("결제 실패");
       }
    }

    private void validateAndSave(PaymentInfo paymentInfo) throws MessagingException {
        //결제 검증 -> 저장 -> 구매한 책 장바구니에서 제거 -> 구매 확정 메일 전송
        paymentService.paymentPriceValidate(paymentInfo.getBookId(),paymentInfo.getPaymentPrice());
        paymentService.orderNumberValidate(paymentInfo.getOrderNumber());
        paymentService.checkPaymentNull(paymentInfo);

        paymentMapper.paymentInfoSave(paymentInfo);
        bookCartMapper.deleteBookCart(paymentInfo.getBookId(), paymentInfo.getBuyerEmail());
        emailService.sendPaymentMessage(paymentInfo);
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
        return exchange;
    }

}
