package com.ourbook.shop.controller.shopController;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.book.BookCartSave;
import com.ourbook.shop.dto.book.BookSearchResult;
import com.ourbook.shop.service.shopService.BookCartService;
import com.ourbook.shop.service.shopService.FindBookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/OurBook/book")
@Controller
public class ShopController {

    private final BookCartService bookCartService;

    private final FindBookService findBookService;

    @Autowired
    public ShopController(BookCartService bookCartService, FindBookService findBookService) {
        this.bookCartService = bookCartService;
        this.findBookService = findBookService;
    }

    @PostMapping("/info/cart")
    public String bookCartInsert(@ModelAttribute BookCartSave bookCartSave, HttpServletRequest request, @AuthenticationPrincipal CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            bookCartSave.setEmail(naverMember.getEmail());
            bookCartService.insertBookCart(bookCartSave);
        }else{
            bookCartSave.setEmail(userDetail.getEmail());
            bookCartService.insertBookCart(bookCartSave);
        }
        return "redirect:/OurBook/book/info/"+ bookCartSave.getBookId();
    }


    @DeleteMapping("/info/cart/{bookId}")
    public String bookCartDelete(@PathVariable("bookId")String bookId, HttpServletRequest request, @AuthenticationPrincipal CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            bookCartService.deleteBookCart(bookId,naverMember.getEmail());
        }else{
            bookCartService.deleteBookCart(bookId, userDetail.getEmail());
        }
        return "redirect:/OurBook/book/info/cart";
    }

    @PutMapping("/info/cart/{bookId}")
    public String bookCartUpdate(@PathVariable("bookId")String bookId,@RequestParam("buyCount")Integer bookCount,  HttpServletRequest request,
                                 @AuthenticationPrincipal CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            bookCartService.updateBookCart(bookCount,bookId,naverMember.getEmail());
        }else{
            bookCartService.updateBookCart(bookCount,bookId,userDetail.getEmail());
        }
        return "redirect:/OurBook/book/info/cart";
    }



    @PostMapping("/search")
    public String bookSearch(@RequestParam String searchBookName, Model model){
        List<BookSearchResult> bookSearchResults = findBookService.userSearchBook(searchBookName);
        model.addAttribute("bookSearchResults",bookSearchResults);
        return "books/bookSearch";
    }






}
