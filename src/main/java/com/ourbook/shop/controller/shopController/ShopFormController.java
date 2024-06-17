package com.ourbook.shop.controller.shopController;
import com.ourbook.shop.config.auth.session.SessionUser;
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

import java.util.List;

@Slf4j
@RequestMapping("/OurBook")
@Controller
public class ShopFormController {

    private final FindBookService findBookService;

    private final BookCartService bookCartService;


    @Autowired
    public ShopFormController(FindBookService findBookService, BookCartService bookCartService) {
        this.findBookService = findBookService;
        this.bookCartService = bookCartService;
    }


    @GetMapping("/book")
    public String bookListView(Model model){
        List<Book> allBook = findBookService.findAllBook();
        model.addAttribute("allBook",allBook);
        return "books/bookList";
    }


    @GetMapping("/book/info/{bookId}")
    public String bookInfoView(@PathVariable String bookId, Model model){
        Book book = findBookService.findBook(bookId);
        model.addAttribute("bookInfo",book);
        return "books/bookInfo";
    }


    @GetMapping("/book/info/cart")
    public String bookCartView(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetail userDetail,Model model){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            List<BookCartView> cartToNaverEmail = bookCartService.findCartToEmail(naverMember.getEmail());
            model.addAttribute("MyCartBooks",cartToNaverEmail);
        }else{
            List<BookCartView> cartToCommonEmail = bookCartService.findCartToEmail(userDetail.getEmail());
            model.addAttribute("MyCartBooks",cartToCommonEmail);
        }
            return "books/bookCart";
    }

}
