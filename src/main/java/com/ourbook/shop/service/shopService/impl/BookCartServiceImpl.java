package com.ourbook.shop.service.shopService.impl;

import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.dto.book.BookCartSave;
import com.ourbook.shop.dto.book.BookCartView;
import com.ourbook.shop.mapper.shopMapper.BookCartMapper;
import com.ourbook.shop.mapper.shopMapper.FindBookMapper;
import com.ourbook.shop.service.shopService.BookCartService;
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
    public void deleteBookCart(String bookId, String email) {
        bookCartMapper.deleteBookCart(bookId,email);
    }

    /** 장바구니 검색 및 출력 기능 (findCartToEmail , getMyCartInfo) 의 분석 메모는 노션 9/12 참고 **/
    @Override
    public List<BookCartView> findCartToEmail(String email) {
        List<Map<String, Object>> cartToEmail = bookCartMapper.findCartToEmail(email);
        List<BookCartView> cartView = getMyCartInfo(cartToEmail);
        return cartView;
    }


    private List<BookCartView> getMyCartInfo(List<Map<String, Object>> cartToEmail) {
        return cartToEmail.stream()
                .map(item -> {
                    String bookId = (String) item.get("bookId");
                    int bookCount = (int) item.get("bookCount");
                    Book book = findBookMapper.findBook(bookId);
                    book.setBookPrice(book.getBookPrice().setScale(0));
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
