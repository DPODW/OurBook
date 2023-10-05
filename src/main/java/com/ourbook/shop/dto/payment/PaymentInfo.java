package com.ourbook.shop.dto.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@ToString
@Getter
@Setter
public class PaymentInfo {
    String orderNumber;


    String bookName;

    String bookId;

    int paymentBookCount;

    BigDecimal paymentPrice;

    String buyerEmail;

    String buyerName;

    String receiverName;

    String receiverAddress;

    String receiverPhoneNumber;

    String paymentNumber;
    String paymentTime;


    public PaymentInfo(String orderNumber, String bookName, String bookId, int paymentBookCount, BigDecimal paymentPrice, String buyerEmail,
                       String buyerName, String receiverName, String receiverAddress, String receiverPhoneNumber, String paymentNumber, String paymentTime) {
        this.orderNumber = orderNumber;
        this.bookName = bookName;
        this.bookId = bookId;
        this.paymentBookCount = paymentBookCount;
        this.paymentPrice = paymentPrice;
        this.buyerEmail = buyerEmail;
        this.buyerName = buyerName;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.paymentNumber = paymentNumber;
        this.paymentTime = paymentTime;
    }

}
