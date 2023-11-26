package com.ourbook.shop.service.shopService.impl;

import com.ourbook.shop.dto.book.BookSearchResult;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.mapper.paymentMapper.PaymentMapper;
import com.ourbook.shop.mapper.shopMapper.FindBookMapper;
import com.ourbook.shop.service.shopService.FindBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public String findBookImg(String bookId) {
        return findBookMapper.findBookImg(bookId);
    }

    @Override
    public BigDecimal findBookPrice(String bookId) {
       return findBookMapper.findBookPrice(bookId);
    }

    @Override
    public List<BookSearchResult> userSearchBook(String searchBookName) {
        List<BookSearchResult> bookSearchResults = findBookMapper.userSearchBook(searchBookName.replaceAll("\\s", ""));
        bookSearchResults.stream()
                .map(bookSearchResult -> {
                    if(bookSearchResult.getCategory().equals("market_books")){
                        bookSearchResult.setCategory("OurBook 도서 시장");
                    }else{
                        bookSearchResult.setCategory("OurBook 도서 스토어");
                    }
                    return bookSearchResult;
                }).collect(Collectors.toList());

        return bookSearchResults;
    }
}



