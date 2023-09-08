package com.ourbook.shop.service.impl;

import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.dto.book.BookCart;
import com.ourbook.shop.mapper.shopMapper.BookCartMapper;
import com.ourbook.shop.mapper.shopMapper.FindBookMapper;
import com.ourbook.shop.service.BookCartService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BookCartServiceImpl implements BookCartService {

    private final BookCartMapper bookCartMapper;

    private final FindBookMapper findBookMapper;


    @Autowired
    public BookCartServiceImpl(BookCartMapper bookCartMapper, FindBookMapper findBookMapper) {
        this.bookCartMapper = bookCartMapper;
        this.findBookMapper = findBookMapper;
    }

    @Override
    public void insertBookCart(BookCart bookCart) {
        if(bookCartMapper.findBookCart(bookCart.getEmail(),bookCart.getBookId())!=null){
            bookCartMapper.updateBookCount(bookCart);
        }else{
            bookCartMapper.insertBookCart(bookCart);
        }
    }

    @Override
    public Map<String, Object> findCartToEmail(String email) {
        List<Map<String, Object>> cartToEmail = bookCartMapper.findCartToEmail(email);

        Map<String, Object> result = getMyCartInfo(cartToEmail);

        return result;
    }


    private Map<String, Object> getMyCartInfo(List<Map<String, Object>> cartToEmail) {
        List<String> bookIds = new ArrayList<>();
        List<Integer> bookCounts = new ArrayList<>();
        List<Book> allBooks = new ArrayList<>();

        for (Map<String, Object> item : cartToEmail) {
            int bookCount = (int) item.get("bookCount");
            String bookId = (String) item.get("bookId");


            bookIds.add(bookId);
            bookCounts.add(bookCount);
        }

        for(String bookId:bookIds){
            Book book = findBookMapper.findBook(bookId);
            allBooks.add(book);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("allBooks", allBooks);
        result.put("bookCounts", bookCounts);
        return result;
    }


}
