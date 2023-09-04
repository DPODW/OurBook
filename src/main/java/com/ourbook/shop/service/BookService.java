package com.ourbook.shop.service;

import com.ourbook.shop.dto.book.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    Book findBook(String id);

}
