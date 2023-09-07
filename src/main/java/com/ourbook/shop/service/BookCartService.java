package com.ourbook.shop.service;

import com.ourbook.shop.dto.book.BookCart;
import org.springframework.stereotype.Service;

@Service
public interface BookCartService {

    void insertBookCart(BookCart bookCart);


}
