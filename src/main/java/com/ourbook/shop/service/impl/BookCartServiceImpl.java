package com.ourbook.shop.service.impl;

import com.ourbook.shop.dto.book.BookCart;
import com.ourbook.shop.mapper.shopMapper.BookCartMapper;
import com.ourbook.shop.service.BookCartService;
import org.springframework.stereotype.Service;

@Service
public class BookCartServiceImpl implements BookCartService {

    private final BookCartMapper bookCartMapper;



    public BookCartServiceImpl(BookCartMapper bookCartMapper) {
        this.bookCartMapper = bookCartMapper;
    }

    @Override
    public void insertBookCart(BookCart bookCart) {
        if(bookCartMapper.findBookCart(bookCart.getEmail(),bookCart.getBookId())!=null){
            bookCartMapper.updateBookCount(bookCart);
        }else{
            bookCartMapper.insertBookCart(bookCart);
        }
    }
}
