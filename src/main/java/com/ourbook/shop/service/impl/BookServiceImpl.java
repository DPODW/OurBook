package com.ourbook.shop.service.impl;

import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.mapper.shopMapper.FindBookMapper;
import com.ourbook.shop.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final FindBookMapper findBookMapper;

    public BookServiceImpl(FindBookMapper findBookMapper) {
        this.findBookMapper = findBookMapper;
    }

    @Override
    public Book findBook(String bookId) {
        Book book = findBookMapper.findBook(bookId);
        book.setBookPrice(book.getBookPrice().setScale(0));
        return book;
    }
}
