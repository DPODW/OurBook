package com.ourbook.shop.config.exception;

public class PaymentFailException extends RuntimeException{

    public PaymentFailException(String message) {
        super(message);
    }

}
