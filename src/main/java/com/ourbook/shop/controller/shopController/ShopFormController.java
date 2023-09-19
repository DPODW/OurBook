package com.ourbook.shop.controller.shopController;
import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.dto.book.BookCartView;
import com.ourbook.shop.service.shopService.BookCartService;
import com.ourbook.shop.service.shopService.FindBookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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
    public String bookList(Model model){
        List<Book> allBook = findBookService.findAllBook();
        model.addAttribute("allBook",allBook);
        return "ourBookShop/bookList";
    }


    @GetMapping("/OurBook/book/info/{bookId}")
    public String bookInfo(@PathVariable String bookId, Model model){
        Book book = findBookService.findBook(bookId);
        model.addAttribute("bookInfo",book);
        return "ourBookShop/bookInfo";
    }


    @GetMapping("/OurBook/book/info/cart")
    public String bookCart(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetail userDetail,Model model){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            List<BookCartView> cartToNaverEmail = bookCartService.findCartToEmail(naverMember.getEmail());
            model.addAttribute("MyCartBooks",cartToNaverEmail);
        }else{
            List<BookCartView> cartToCommonEmail = bookCartService.findCartToEmail(userDetail.getEmail());
            model.addAttribute("MyCartBooks",cartToCommonEmail);
        }
            return "ourBookShop/bookCart";
    }

    @GetMapping("/OurBook/book/info/cart/{bookId}")
    public String paymentInfo(@PathVariable("bookId")String bookId, @RequestParam("bookCount") BigDecimal bookCount, HttpServletRequest request,
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
        return "ourBookShop/paymentInfo";
    }

    private void purchaseBookInfo(String bookId,BigDecimal bookCount, Model model) {
        Book book = findBookService.findBook(bookId);
        model.addAttribute("paymentInfo",book);
        model.addAttribute("bookCount",bookCount);

    }


}
