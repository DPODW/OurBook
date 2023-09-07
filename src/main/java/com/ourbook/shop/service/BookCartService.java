package com.ourbook.shop.service;

import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.dto.book.BookCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookCartService {

    void insertBookCart(BookCart bookCart);

    List<BookCart> findCartToEmail(String email);
}
