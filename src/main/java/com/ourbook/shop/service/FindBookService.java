package com.ourbook.shop.service;

import com.ourbook.shop.dto.book.Book;
import org.springframework.stereotype.Service;

@Service
public interface FindBookService {

    Book findBook(String id);

}
