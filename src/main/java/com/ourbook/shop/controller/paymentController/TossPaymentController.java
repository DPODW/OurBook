package com.ourbook.shop.controller.paymentController;

import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.service.additionService.emailService.EmailService;
import com.ourbook.shop.service.paymentService.PaymentService;
import com.ourbook.shop.service.paymentService.TossPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    /** 토스페이먼츠의 결제 API 제어하는 컨트롤러 **/

    private final TossPaymentService tossPaymentService;

    private final PaymentService paymentService;

    private final EmailService emailService;

    public TossPaymentController(TossPaymentService tossPaymentService, PaymentService paymentService, EmailService emailService) {
        this.tossPaymentService = tossPaymentService;
        this.paymentService = paymentService;

        this.emailService = emailService;
    }

    @GetMapping("/TossPay/validate")
    public String TossPaymentValidateAndSave(@RequestParam String orderId, @RequestParam String paymentKey, @RequestParam String amount, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("TossPaymentInfo")==null){
            log.error("토스페이 결제 정보 저장 세션이 생성되지 않았습니다.");
            return "redirect:/OurBook/book/info/payment/fail";
        }else{
            tossPaymentService.TossPaymentValidate(orderId, paymentKey, amount);
            PaymentInfo TossPaymentInfo = (PaymentInfo) session.getAttribute("TossPaymentInfo");
            PaymentInfo TossResultPaymentInfo = tossPaymentService.TossPaymentInfoSave(TossPaymentInfo, paymentKey);
            session.removeAttribute("TossPaymentInfo");
            return "redirect:/OurBook/book/info/payment/result/"+TossResultPaymentInfo.getOrderNumber();
        }
    }


    @PostMapping("/TossPay/payment/1")
    public ResponseEntity<Void> TossPaymentInfoSession(@RequestBody PaymentInfo paymentInfo, HttpServletRequest request){
        HttpSession TossPaymentInfo = request.getSession();
        TossPaymentInfo.setAttribute("TossPaymentInfo",paymentInfo);
        return ResponseEntity.ok().build();
    }



}
