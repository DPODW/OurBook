package com.ourbook.shop.controller.paymentController;

import com.ourbook.shop.dto.PayMent.PaymentInfo;
import com.ourbook.shop.service.paymentService.TossPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class TossPaymentController {

    private String paymentKey;
    private final TossPaymentService tossPaymentService;

    public TossPaymentController(TossPaymentService tossPaymentService) {
        this.tossPaymentService = tossPaymentService;

    }

    @GetMapping("/TossPay/validate")
    public ResponseEntity<String> TossPaymentValidate(@RequestParam String orderId, @RequestParam String paymentKey, @RequestParam String amount){
        ResponseEntity<String> stringResponseEntity = tossPaymentService.paymentValidate(orderId, paymentKey, amount);
        this.paymentKey = paymentKey;
        return stringResponseEntity;
    }

    @PostMapping("/TossPay/payment/1")
    public String TossPaymentInfo(@RequestBody PaymentInfo paymentInfo){
        log.info("{}",paymentInfo);
        log.info("{}", this.paymentKey);
        return "main/Main";
    }

}
