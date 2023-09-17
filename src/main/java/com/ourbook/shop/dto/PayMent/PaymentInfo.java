package com.ourbook.shop.dto.PayMent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
public class PaymentInfo {

    String bookName;

    String bookId;

    String paymentBookCount;

    BigDecimal paymentPrice;

    String buyerEmail;

    String buyerName;

    String receiverName;

    String receiverAddress;

    String receiverPhoneNumber;

    public PaymentInfo(String bookName, String bookId, String paymentBookCount, BigDecimal paymentPrice, String buyerEmail, String buyerName, String receiverName, String receiverAddress, String receiverPhoneNumber) {
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
