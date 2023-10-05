package com.ourbook.shop.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class PaymentFailExceptionHandler {

    @ExceptionHandler(PaymentFailException.class)
    public ResponseEntity<String> handlePaymentFailException(PaymentFailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제 실패");
    }
}
