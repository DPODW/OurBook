package com.ourbook.shop.mail;

import com.ourbook.shop.dto.market.PurchaseRequest;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.service.additionService.emailService.EmailService;
import com.ourbook.shop.service.additionService.emailService.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import org.thymeleaf.spring6.SpringTemplateEngine;

@SpringBootTest
public class MailSendTest {

    private final SpringTemplateEngine templateEngine;

    private EmailService emailService;

    @Autowired
    public MailSendTest(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @BeforeEach
    void setup(@Autowired JavaMailSender javaMailSender) {
        //JavaMailSender 에 @Autowired 할 시, bean 을 찾지 못하는 오류로 인하여
        //메서드의 파라미터로 @Autowired 를 사용하여 JavaMailSender 빈을 명시적으로 주입.

        emailService = new EmailServiceImpl(javaMailSender, templateEngine);
    }

    @Test
    @DisplayName("결제 확정 메일 정상 전송되는지 테스트")
    void sendPaymentMessage() throws MessagingException {
        /* given */
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setBuyerEmail("test@naver.com");

        /* when ~ then */
        emailService.sendPaymentMessage(paymentInfo);

    }

    @Test
    @DisplayName("도서시장 구매요청 메일 정상 전송되는지 테스트")
    void sendPurchaseRequestMessage() throws MessagingException {
        /* given */
        PurchaseRequest purchaseRequest = new PurchaseRequest();

        purchaseRequest.setReceiverName("김리시버님");
        purchaseRequest.setUploaderName("문업로드님");
        purchaseRequest.setUploaderEmail("test@naver.com");

        /* when ~ then */
        emailService.sendPurchaseRequestMessage(purchaseRequest);
    }

}
