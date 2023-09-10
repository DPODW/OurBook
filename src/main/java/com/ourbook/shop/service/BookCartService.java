package com.ourbook.shop.service;

import com.ourbook.shop.dto.book.BookCartSave;
import com.ourbook.shop.dto.book.BookCartView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookCartService {

    void insertBookCart(BookCartSave bookCartSave);

    List<BookCartView> findCartToEmail(String email);


}
