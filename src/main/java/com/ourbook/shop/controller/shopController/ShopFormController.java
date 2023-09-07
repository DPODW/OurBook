package com.ourbook.shop.controller.shopController;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.dto.book.BookCart;
import com.ourbook.shop.service.BookCartService;
import com.ourbook.shop.service.FindBookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class ShopFormController {

    private final FindBookService findBookService;

    private final BookCartService bookCartService;


    @Autowired
    public ShopFormController(FindBookService findBookService, BookCartService bookCartService) {
        this.findBookService = findBookService;
        this.bookCartService = bookCartService;
    }


    @GetMapping("/OurBook/book")
    public String bookList(){
        return "ourBookShop/bookList";
    }


    @GetMapping("/OurBook/book/info/{bookId}")
    public String bookInfo(@PathVariable String bookId, Model model){
        Book book = findBookService.findBook(bookId);
        model.addAttribute("bookInfo",book);
        return "ourBookShop/bookInfo";
    }

    @PostMapping("/OurBook/book/info/cart")
    public String bookCart(@ModelAttribute BookCart bookCart, HttpServletRequest request,CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            bookCart.setEmail(naverMember.getEmail());
            bookCartService.insertBookCart(bookCart);
        }else{
            bookCart.setEmail(userDetail.getEmail());
            bookCartService.insertBookCart(bookCart);
        }
        return "ourBookShop/bookCart";
    }

}
