package com.ourbook.shop.config.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentFailException extends RuntimeException{

    public PaymentFailException(String message) {
        super(message);
    }

}
