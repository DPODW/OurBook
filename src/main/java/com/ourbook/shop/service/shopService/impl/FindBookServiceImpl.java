package com.ourbook.shop.service.shopService.impl;

import com.ourbook.shop.dto.PayMent.PaymentInfo;
import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.mapper.paymentMapper.PaymentMapper;
import com.ourbook.shop.mapper.shopMapper.FindBookMapper;
import com.ourbook.shop.service.paymentService.PaymentService;
import com.ourbook.shop.service.shopService.FindBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class FindBookServiceImpl implements FindBookService {

    private final FindBookMapper findBookMapper;

    private final PaymentMapper paymentMapper;

    public FindBookServiceImpl(FindBookMapper findBookMapper,PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
        this.findBookMapper = findBookMapper;
    }

    @Override
    public Book findBook(String bookId) {
        Book book = findBookMapper.findBook(bookId);
        book.setBookPrice(book.getBookPrice().setScale(0));
        return book;
    }

    @Override
    public List<Book> findAllBook() {
        return findBookMapper.findAllBook();
    }

    @Override
    public PaymentInfo orderNumberToBook(String orderNumber) {
        return paymentMapper.findOrderNumber(orderNumber);
    }
}



