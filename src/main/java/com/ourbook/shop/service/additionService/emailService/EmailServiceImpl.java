package com.ourbook.shop.service.additionService.emailService;

import com.ourbook.shop.dto.market.PurchaseRequest;
import com.ourbook.shop.dto.payment.PaymentInfo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final SpringTemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendPaymentMessage(PaymentInfo paymentInfo) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        try {
            helper.setTo(paymentInfo.getBuyerEmail());
            helper.setSubject("OurBook에서 결제 확정 메일 보내드립니다!");
            String htmlContent = setPaymentContext(paymentInfo);
            helper.setText(htmlContent, true); // 두 번째 매개변수를 true로 설정하여 HTML을 파싱
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendPurchaseRequestMessage(PurchaseRequest purchaseRequest) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        purchaseRequest.setReceiverName(nameLock(purchaseRequest.getReceiverName()));
        purchaseRequest.setUploaderName(nameLock(purchaseRequest.getUploaderName()));
        try {
            helper.setTo(purchaseRequest.getUploaderEmail());
            helper.setSubject("OurBook에서 구매 요청 메일을 보내드립니다!");
            String htmlContent = setPurchaseRequestContext(purchaseRequest);
            helper.setText(htmlContent, true); // 두 번째 매개변수를 true로 설정하여 HTML을 파싱
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String setPaymentContext(PaymentInfo paymentInfo) { // 타임리프 설정하는 코드
        Context context = new Context();
        context.setVariable("paymentInfoMail", paymentInfo); // Template에 전달할 데이터 설정
        return templateEngine.process("mail/paymentSuccessMail", context); // mail.html
    }


    private String setPurchaseRequestContext(PurchaseRequest purchaseRequest) { // 타임리프 설정하는 코드
        Context context = new Context();
        context.setVariable("purchaseRequestMail", purchaseRequest); // Template에 전달할 데이터 설정
        return templateEngine.process("mail/purchaseRequestMail", context); // mail.html
    }

    /** 11/1 노션 정리 **/
    public String nameLock(String input) {
        //이름 중간을 '*' 로 치환하는 메소드. (개인정보 보호)
        char firstChar = input.charAt(0);
        char lastChar = input.charAt(input.length() - 1);
        String middleMasked = "*".repeat(input.length() - 2);
        return firstChar + middleMasked + lastChar;
    }

}
