package com.ourbook.shop.controller.shopController;


import com.ourbook.shop.dto.PayMent.PaymentInfo;
import com.ourbook.shop.service.paymentService.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;


@Slf4j
@RestController
public class PaymentController {
    private final PaymentService paymentService;
    private IamportClient api;


    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
        this.api = new IamportClient("2118630761121164","WDBzotbTcGQeF9aR70IHfxXE6Zqtj9XtpkSGHJA1RVFFX3xRHuXN94dmO63bgncxwGeDhoENCpybZQWu");
    }

    @PostMapping("/OurBook/payment/1")
    public ResponseEntity<String> paymentInfoSave(@RequestBody PaymentInfo paymentInfo){
        paymentService.paymentInfoSave(paymentInfo);
        return ResponseEntity.ok().body("결제 정보 저장 성공");
    }

    @PostMapping("/OurBook/payment/check")
    public ResponseEntity<String> paymentValidate(@RequestBody Map<String,String> orderNumber){
        paymentService.paymentValidate(orderNumber.get("orderNumber"));
        return ResponseEntity.ok().body("결제 검증 성공");
    }

    @ResponseBody
    @PostMapping("/verifyIamport/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid) throws IamportResponseException, IOException{
        return api.paymentByImpUid(imp_uid);
    }

}
