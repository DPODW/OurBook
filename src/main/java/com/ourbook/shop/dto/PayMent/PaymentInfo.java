package com.ourbook.shop.dto.PayMent;

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

    public PaymentInfo(String orderNumber, String bookName, String bookId, int paymentBookCount, BigDecimal paymentPrice, String buyerEmail, String buyerName, String receiverName, String receiverAddress, String receiverPhoneNumber) {
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
    }


}
