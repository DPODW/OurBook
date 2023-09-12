package com.ourbook.shop.service;

import com.ourbook.shop.dto.book.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FindBookService {

    Book findBook(String id);

    List<Book> findAllBook();

}
