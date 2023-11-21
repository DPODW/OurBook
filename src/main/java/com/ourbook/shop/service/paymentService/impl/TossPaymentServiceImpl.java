package com.ourbook.shop.service.paymentService.impl;

import com.ourbook.shop.config.exception.PaymentFailException;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.mapper.paymentMapper.PaymentMapper;
import com.ourbook.shop.mapper.shopMapper.BookCartMapper;
import com.ourbook.shop.service.additionService.emailService.EmailService;
import com.ourbook.shop.service.paymentService.PaymentAPIkey;
import com.ourbook.shop.service.paymentService.PaymentService;
import com.ourbook.shop.service.paymentService.TossPaymentService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Service
public class TossPaymentServiceImpl implements TossPaymentService {

    private final PaymentMapper paymentMapper;

    private final PaymentService paymentService;

    private final BookCartMapper bookCartMapper;

    private final EmailService emailService;


    private final PaymentAPIkey paymentAPIkey;

    public TossPaymentServiceImpl(PaymentMapper paymentMapper, PaymentService paymentService, BookCartMapper bookCartMapper, EmailService emailService, PaymentAPIkey paymentAPIkey) {
        this.paymentMapper = paymentMapper;
        this.paymentService = paymentService;
        this.bookCartMapper = bookCartMapper;
        this.emailService = emailService;
        this.paymentAPIkey = paymentAPIkey;
    }


    @Override
    public PaymentInfo TossPaymentInfoSave(PaymentInfo paymentInfo,String paymentKey) {
        try{
            validateAndSave(paymentInfo, paymentKey);
            return paymentInfo;
        }catch (Exception ex){
            TossPaymentCancel(paymentKey);
            if(paymentMapper.findOrderNumber(paymentInfo.getOrderNumber())!=null){
                paymentMapper.paymentInfoDelete(paymentInfo.getOrderNumber()); //결제 내역이 이미 생성되어버렸다면 -> 제거
            }
            log.error("결제 내역 저장 실패. 결제 금액은 즉시 환불 됩니다. 예외 발생 위치=> {}",ex.getStackTrace()[0]);
            throw new PaymentFailException("결제 실패");
        }
    }

    private void validateAndSave(PaymentInfo paymentInfo, String paymentKey) throws MessagingException {
        //결제 검증 -> 저장 -> 구매한 책 장바구니에서 제거 -> 구매 확정 메일 전송
        paymentService.orderNumberValidate(paymentInfo.getOrderNumber());
        paymentService.checkPaymentNull(paymentInfo);
        paymentInfo.setPaymentNumber(paymentKey);
        paymentMapper.paymentInfoSave(paymentInfo);
        bookCartMapper.deleteBookCart(paymentInfo.getBookId(), paymentInfo.getBuyerEmail());
        emailService.sendPaymentMessage(paymentInfo);
    }

    @Override
    public void TossPaymentValidate(String orderId, String paymentKey, String amount) {
        String toss_sk = new String(Base64.getEncoder().encode(paymentAPIkey.getTOSS_secretKey().getBytes(StandardCharsets.UTF_8)));
        try{
            String apiUrl = "https://api.tosspayments.com/v1/payments/confirm";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "application/json");
            headers.set("Authorization", "Basic "+toss_sk);

            String requestBody = "{\"amount\":\"" + amount + "\",\"orderId\":\"" + orderId +
                    "\",\"paymentKey\":\""+ paymentKey + "\"}";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
            log.info("토스페이 검증 결과 입니다. -> {}",responseEntity);
        }catch (Exception ex){
            log.error("토스 검증 API 통과 실패. 결제는 이루어지지 않습니다. {}",ex.getStackTrace()[0]);
            throw new PaymentFailException("결제 실패");
        }
    }


    @Override
    public void TossPaymentCancel(String paymentKey) {
        String toss_sk = new String(Base64.getEncoder().encode(paymentAPIkey.getTOSS_secretKey().getBytes(StandardCharsets.UTF_8)));
        String apiUrl = "https://api.tosspayments.com/v1/payments/"+paymentKey+"/cancel";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        headers.set("Authorization", "Basic "+toss_sk);

        String requestBody = "{\"cancelReason\":\"" + "결제 오류로 인한 결제 취소" + "\"}";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
        log.info("토스페이 결제 취소 결과 입니다. -> {}",responseEntity);
    }

}
