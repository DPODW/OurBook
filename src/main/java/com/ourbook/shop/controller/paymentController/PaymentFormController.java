package com.ourbook.shop.controller.paymentController;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.service.shopService.FindBookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Slf4j
@Controller
public class PaymentFormController {

    private final FindBookService findBookService;

    public PaymentFormController(FindBookService findBookService) {
        this.findBookService = findBookService;
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

    private void purchaseBookInfo(String bookId,BigDecimal bookCount, Model model) {
        Book book = findBookService.findBook(bookId);
        model.addAttribute("paymentInfo",book);
        model.addAttribute("bookCount",bookCount);

    }
}
