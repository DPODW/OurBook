package com.ourbook.shop.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class PaymentFailExceptionHandler {

    @ExceptionHandler(PaymentFailException.class)
    public ModelAndView handlePaymentFailException(PaymentFailException ex) {
       log.info("{}",ex.getStackTrace());
       ModelAndView mav = new ModelAndView();
       mav.setViewName("TEST9");
       return mav;
    }
}
