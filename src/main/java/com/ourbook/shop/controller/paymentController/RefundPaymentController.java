package com.ourbook.shop.controller.paymentController;

import com.ourbook.shop.dto.payment.KGPaymentCancel;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.service.paymentService.KGPaymentService;
import com.ourbook.shop.service.paymentService.PaymentAPIkey;
import com.ourbook.shop.service.paymentService.PaymentService;
import com.ourbook.shop.service.paymentService.TossPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
public class RefundPaymentController {

    private final PaymentService paymentService;

    private final PaymentAPIkey paymentAPIkey;

    private final TossPaymentService tossPaymentService;

    private final KGPaymentService KGPaymentService;


    public RefundPaymentController(PaymentService paymentService, PaymentAPIkey paymentAPIkey, TossPaymentService tossPaymentService, KGPaymentService KGPaymentService) {
        this.paymentService = paymentService;
        this.paymentAPIkey = paymentAPIkey;
        this.tossPaymentService = tossPaymentService;
        this.KGPaymentService = KGPaymentService;
    }

    @PostMapping("/payment/refund")
    public String paymentRefundInfo(@RequestParam String orderNumber, Model model){
        PaymentInfo refundPaymentInfo = paymentService.findOrderNumber(orderNumber);
        model.addAttribute("refundPaymentInfo",refundPaymentInfo);
        return "payment/paymentRefund";
    }




    @PostMapping("/payment/refund/request")
    public String paymentRefund(@RequestParam String orderNumber){
        PaymentInfo refundPaymentInfo = paymentService.findOrderNumber(orderNumber);
        if(refundPaymentInfo.getPaymentType().equals("NHPay")){
            String accessToken = KGPaymentService.getIamportAccessToken(paymentAPIkey.getIAMPORT_imp_key(), paymentAPIkey.getIAMPORT_imp_secret());
            KGPaymentCancel kgPaymentCancelInfo = new KGPaymentCancel(accessToken,refundPaymentInfo.getPaymentNumber(),"사용자 요구에 의한 환불(결제 취소)",refundPaymentInfo.getPaymentPrice());
            KGPaymentService.paymentCancel(kgPaymentCancelInfo);
        }else{
            tossPaymentService.TossPaymentCancel(refundPaymentInfo.getPaymentNumber());
        }
        paymentService.paymentInfoDelete(orderNumber);
        return "payment/paymentRefundSuccess";
    }


}
