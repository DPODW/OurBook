package com.ourbook.shop.service.additionService.emailService;

import com.ourbook.shop.dto.market.PurchaseRequest;
import com.ourbook.shop.dto.payment.PaymentInfo;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendPaymentMessage(PaymentInfo paymentInfo) throws MessagingException;

    void sendPurchaseRequestMessage(PurchaseRequest purchaseRequest) throws MessagingException;

}
