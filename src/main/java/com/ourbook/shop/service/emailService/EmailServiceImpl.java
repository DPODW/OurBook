package com.ourbook.shop.service.emailService;

import com.ourbook.shop.dto.payment.PaymentInfo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
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
            String htmlContent = setContext(paymentInfo);
            helper.setText(htmlContent, true); // 두 번째 매개변수를 true로 설정하여 HTML을 파싱
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String setContext(PaymentInfo paymentInfo) { // 타임리프 설정하는 코드
        Context context = new Context();
        context.setVariable("paymentInfoMail", paymentInfo); // Template에 전달할 데이터 설정
        return templateEngine.process("mail/paymentSuccessMail", context); // mail.html
    }

}
