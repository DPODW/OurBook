package com.ourbook.shop.controller.shopController;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.book.BookCart;
import com.ourbook.shop.service.BookCartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ShopController {

    private final BookCartService bookCartService;

    public ShopController(BookCartService bookCartService) {
        this.bookCartService = bookCartService;
    }

    @PostMapping("/OurBook/book/info/cart")
    public String bookCart(@ModelAttribute BookCart bookCart, HttpServletRequest request, @AuthenticationPrincipal CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            bookCart.setEmail(naverMember.getEmail());
            bookCartService.insertBookCart(bookCart);
        }else{
            bookCart.setEmail(userDetail.getEmail());
            bookCartService.insertBookCart(bookCart);
        }
        return "redirect:/OurBook/book/info/"+bookCart.getBookId();
    }
}
