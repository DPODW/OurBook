package com.ourbook.shop.mapper.shopMapper;

import com.ourbook.shop.dto.book.Book;
import com.ourbook.shop.dto.book.BookSearchResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Mapper
public interface FindBookMapper {

    Book findBook(String bookId);

    List<Book> findAllBook();

    String findBookImg(String bookId);

    BigDecimal findBookPrice(String bookId);

    List<BookSearchResult> userSearchBook(String searchBookName);
}
