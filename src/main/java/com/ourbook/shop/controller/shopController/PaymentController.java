package com.ourbook.shop.controller.shopController;


import com.ourbook.shop.dto.PayMent.PaymentInfo;
import com.ourbook.shop.service.paymentService.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/OurBook/payment/1")
    public ResponseEntity<String> paymentInfoSave(@RequestBody PaymentInfo paymentInfo){
        paymentService.paymentInfoSave(paymentInfo);
        return ResponseEntity.ok().body("ok");
    }
}
