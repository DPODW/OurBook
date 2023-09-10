package com.ourbook.shop.service.impl;

import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.dto.book.BookCartSave;
import com.ourbook.shop.dto.book.BookCartView;
import com.ourbook.shop.mapper.shopMapper.BookCartMapper;
import com.ourbook.shop.mapper.shopMapper.FindBookMapper;
import com.ourbook.shop.service.BookCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void insertBookCart(BookCartSave bookCartSave) {
        if(bookCartMapper.findBookCart(bookCartSave.getEmail(), bookCartSave.getBookId())!=null){
            bookCartMapper.updateBookCount(bookCartSave);
        }else{
            bookCartMapper.insertBookCart(bookCartSave);
        }
    }

    @Override
    public List<BookCartView> findCartToEmail(String email) {
        List<Map<String, Object>> cartToEmail = bookCartMapper.findCartToEmail(email);
        List<BookCartView> result = getMyCartInfo(cartToEmail);
        return result;
    }


    private List<BookCartView> getMyCartInfo(List<Map<String, Object>> cartToEmail) {
        return cartToEmail.stream()
                .map(item -> {
                    int bookCount = (int) item.get("bookCount");
                    String bookId = (String) item.get("bookId");
                    Book book = findBookMapper.findBook(bookId);
                    return new BookCartView(
                            book.getBookId(),
                            book.getBookName(),
                            book.getBookPrice(),
                            book.getBookImgUrl(),
                            book.getBookExplan(),
                            bookCount
                    );
                })
                .collect(Collectors.toList());
    }


}
