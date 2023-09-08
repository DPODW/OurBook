package com.ourbook.shop.mapper.shopMapper;

import com.ourbook.shop.dto.book.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FindBookMapper {

    Book findBook(String bookId);



}
