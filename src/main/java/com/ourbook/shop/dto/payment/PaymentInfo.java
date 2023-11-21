package com.ourbook.shop.dto.payment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

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

    String receiverPostCode;

    String receiverAddress;

    String receiverPhoneNumber;

    String paymentNumber;

    String paymentType;
    @Nullable
    String paymentTime;

    @Nullable
    int sequence;


    public PaymentInfo(String orderNumber, String bookName, String bookId, int paymentBookCount, BigDecimal paymentPrice, String buyerEmail,
                       String buyerName, String receiverName,String receiverPostCode, String receiverAddress, String receiverPhoneNumber,
                       String paymentNumber,String paymentType, String paymentTime,int sequence) {
        this.orderNumber = orderNumber;
        this.bookName = bookName;
        this.bookId = bookId;
        this.paymentBookCount = paymentBookCount;
        this.paymentPrice = paymentPrice;
        this.buyerEmail = buyerEmail;
        this.buyerName = buyerName;
        this.receiverName = receiverName;
        this.receiverPostCode = receiverPostCode;
        this.receiverAddress = receiverAddress;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.paymentNumber = paymentNumber;
        this.paymentType = paymentType;
        this.paymentTime = paymentTime;
        this.sequence=sequence;
    }

}
