package com.ourbook.shop.service.additionService.emailService;

import com.ourbook.shop.dto.market.PurchaseRequest;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.mapper.marketMapper.MarketMapper;
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
            helper.setText(htmlContent, true);
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
            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("잘못된 이메일 요청 (이메일 발송 안됌)");
        }
    }

    private String setPaymentContext(PaymentInfo paymentInfo) {
        Context context = new Context();
        context.setVariable("paymentInfoMail", paymentInfo);
        return templateEngine.process("mail/paymentSuccessMail", context);
    }


    private String setPurchaseRequestContext(PurchaseRequest purchaseRequest) {
        Context context = new Context();
        context.setVariable("purchaseRequestMail", purchaseRequest);
        return templateEngine.process("mail/purchaseRequestMail", context);
    }


    /** 이름 중간을 '*' 로 치환하는 메소드. (개인정보 보호) **/
    private static String nameLock(String input) {
        char firstChar = input.charAt(0);
        char lastChar = input.charAt(input.length() - 1);
        String middleMasked = "*".repeat(input.length() - 2);
        return firstChar + middleMasked + lastChar;
    }

}
