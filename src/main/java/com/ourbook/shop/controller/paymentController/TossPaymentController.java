package com.ourbook.shop.controller.paymentController;

import com.ourbook.shop.config.exception.PaymentFailException;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.service.paymentService.TossPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
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

    public TossPaymentController(TossPaymentService tossPaymentService) {
        this.tossPaymentService = tossPaymentService;

    }

    @GetMapping("/TossPay/validate")
    public String TossPaymentValidate(@RequestParam String orderId, @RequestParam String paymentKey, @RequestParam String amount, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("TossPaymentInfo")==null){
            throw new PaymentFailException("토스페이먼츠 결제 내역을 저장하는 세션이 존재하지 않음");
        }else{
            tossPaymentService.TossPaymentValidate(orderId, paymentKey, amount);
            PaymentInfo TossPaymentInfo = (PaymentInfo) session.getAttribute("TossPaymentInfo");
            tossPaymentService.TossPaymentInfoSave(TossPaymentInfo,paymentKey);
            session.removeAttribute("TossPaymentInfo");
            return "redirect:/OurBook/book/info/payment/result/"+orderId;
        }
    }


    @PostMapping("/TossPay/payment/1")
    public String TossPaymentInfo(@RequestBody PaymentInfo paymentInfo, HttpServletRequest request){
        HttpSession TossPaymentInfo = request.getSession();
        TossPaymentInfo.setAttribute("TossPaymentInfo",paymentInfo);
        return "main/Main";
    }

}
