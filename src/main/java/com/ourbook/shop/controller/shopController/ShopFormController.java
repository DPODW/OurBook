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

import java.util.List;
import java.util.Map;

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

    /** map 형태로 책 수량(count) , 이메일을 기준으로 검색한 책 정보 (info) 반환 완료 **/
    @GetMapping("/OurBook/book/info/cart")
    public String bookCart(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            Map<String, Object> cartToEmail = bookCartService.findCartToEmail(naverMember.getEmail());
           log.info("{}",cartToEmail);
        }else{
            Map<String, Object> cartToEmail1 = bookCartService.findCartToEmail(userDetail.getEmail());
            log.info("{}",cartToEmail1);
        }
            return "ourBookShop/bookList";
    }

}
