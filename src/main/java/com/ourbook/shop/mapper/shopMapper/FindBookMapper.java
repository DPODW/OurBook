package com.ourbook.shop.mapper.shopMapper;

import com.ourbook.shop.dto.book.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FindBookMapper {

    Book findBook(String bookId);

    List<Book> findAllBook();

    String findBookImg(String bookId);
}
