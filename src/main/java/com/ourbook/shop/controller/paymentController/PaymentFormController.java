package com.ourbook.shop.controller.paymentController;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.service.paymentService.PaymentService;
import com.ourbook.shop.service.shopService.FindBookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class PaymentFormController {

    private final FindBookService findBookService;

    private final PaymentService paymentService;

    public PaymentFormController(FindBookService findBookService, PaymentService paymentService) {
        this.findBookService = findBookService;
        this.paymentService = paymentService;
    }


    @GetMapping("/OurBook/book/info/payment/{bookId}")
    public String paymentInfoView(@PathVariable("bookId")String bookId, @RequestParam("bookCount") BigDecimal bookCount, HttpServletRequest request,
                                  @AuthenticationPrincipal CustomUserDetail userDetail, Model model){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            purchaseBookInfo(bookId,bookCount,model);
            model.addAttribute("name",naverMember.getName());
            model.addAttribute("email",naverMember.getEmail());
        }else{
            purchaseBookInfo(bookId, bookCount,model);
            model.addAttribute("name",userDetail.getName());
            model.addAttribute("email",userDetail.getEmail());
        }
        return "payment/paymentInfo";
    }


    @GetMapping("/OurBook/book/info/payment/result/{orderNumber}")
    public String paymentSuccessView(@PathVariable String orderNumber, Model model){
        PaymentInfo paymentInfo = findBookService.orderNumberToBook(orderNumber);
        model.addAttribute("paymentInfo",paymentInfo);
        BigDecimal paymentPrice = paymentInfo.getPaymentPrice().setScale(0);
        model.addAttribute("paymentPrice",paymentPrice);
        return "payment/paymentResult";
    }


    @GetMapping("/OurBook/book/info/payment/fail")
    public String paymentFailView(){
        return "payment/paymentfail";
    }


    @GetMapping("/OurBook/book/info/payment/history")
    public String paymentHistoryView(@AuthenticationPrincipal CustomUserDetail userDetail,HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");

            //구매 내역과, 구매한 책의 사진을 가져오기 위한 요청
            List<PaymentInfo> paymentHistory = paymentService.findPaymentHistory(naverMember.getEmail());
            List<String> paymentImg = paymentService.findPaymentImg(paymentHistory);

            paymentHistoryModel(model, paymentHistory, paymentImg);
        }else{
            List<PaymentInfo>  paymentHistory =paymentService.findPaymentHistory(userDetail.getEmail());
            List<String> paymentImg = paymentService.findPaymentImg(paymentHistory);

            paymentHistoryModel(model, paymentHistory, paymentImg);
        }
        return "payment/paymentHistory";
    }


    private static void paymentHistoryModel(Model model, List<PaymentInfo> paymentHistory, List<String> paymentImg) {
        model.addAttribute("bookImages", paymentImg);
        model.addAttribute("paymentHistorys", paymentHistory);
    }


    private void purchaseBookInfo(String bookId,BigDecimal bookCount, Model model) {
        Book book = findBookService.findBook(bookId);
        model.addAttribute("paymentInfo",book);
        model.addAttribute("bookCount",bookCount);

    }
}
