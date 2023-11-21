package com.ourbook.shop.controller.paymentController;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.service.paymentService.KGPaymentService;
import com.ourbook.shop.service.paymentService.PaymentAPIkey;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.io.*;


@Slf4j
@RestController
public class KGPaymentController {
    /** IamPort(포트원)사의 KG 이니시스 결제 API 를 제어하는 컨트롤러 **/
    private final KGPaymentService KGPaymentService;

    private final PaymentAPIkey paymentAPIkey;

    private IamportClient api;

    public KGPaymentController(KGPaymentService KGPaymentService, PaymentAPIkey paymentAPIkey) {
        this.KGPaymentService = KGPaymentService;
        this.paymentAPIkey = paymentAPIkey;
        this.api = new IamportClient(paymentAPIkey.getIAMPORT_imp_key(),paymentAPIkey.getIAMPORT_imp_secret());
    }

    @PostMapping("/OurBook/payment/1")
    public String KGpaymentInfoSave(@RequestBody PaymentInfo paymentInfo){
        PaymentInfo KGpaymentInfo = KGPaymentService.paymentInfoSave(paymentInfo, paymentAPIkey.getIAMPORT_imp_key(), paymentAPIkey.getIAMPORT_imp_secret());
        String KGorderNumber = KGpaymentInfo.getOrderNumber();
        return KGorderNumber;
    }

    @ResponseBody
    @PostMapping("/verifyIamport/{imp_uid}")
    public IamportResponse<Payment> KGpaymentByImpUid(@PathVariable("imp_uid") String imp_uid) throws IamportResponseException, IOException{
        return api.paymentByImpUid(imp_uid);
    }

}
