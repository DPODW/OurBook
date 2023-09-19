package com.ourbook.shop.config.exception;

public class PaymentFailException extends RuntimeException{

    public PaymentFailException() {
        super("결제 실패");
    }
}
