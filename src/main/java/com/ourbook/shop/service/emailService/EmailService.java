package com.ourbook.shop.service.emailService;

import com.ourbook.shop.dto.payment.PaymentInfo;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendPaymentMessage(PaymentInfo paymentInfo) throws MessagingException;
}