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

    String bookCount;

    BigDecimal bookPrice;

    BigDecimal paymentPrice;

    String email;

    String name;

    String address;

    String phoneNumber;

    public PaymentInfo(String bookName, String bookId, String bookCount,BigDecimal bookPrice, BigDecimal paymentPrice, String email, String name, String address, String phoneNumber) {
        this.bookName = bookName;
        this.bookId = bookId;
        this.bookCount = bookCount;
        this.bookPrice = bookPrice;
        this.paymentPrice = paymentPrice;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
