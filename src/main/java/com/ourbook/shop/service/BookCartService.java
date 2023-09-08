package com.ourbook.shop.service;

import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.dto.book.BookCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BookCartService {

    void insertBookCart(BookCart bookCart);

    Map<String, Object> findCartToEmail(String email);


}
