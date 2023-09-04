package com.ourbook.shop.controller.shopController;

import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class ShopFormController {

    private final BookService bookService;

    public ShopFormController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/OurBook/book")
    public String bookList(){
        return "ourBookShop/bookList";
    }


    @GetMapping("/OurBook/book/info/{bookId}")
    public String bookInfo(@PathVariable String bookId, Model model){
        Book book = bookService.findBook(bookId);
        model.addAttribute("bookInfo",book);
        return "ourBookShop/bookInfo";
    }
}
