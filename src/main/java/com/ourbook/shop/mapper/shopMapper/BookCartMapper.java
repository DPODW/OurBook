package com.ourbook.shop.mapper.shopMapper;

import com.ourbook.shop.dto.book.BookCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BookCartMapper {

    void insertBookCart(BookCart bookCart);

    void updateBookCount(BookCart bookCart);

    BookCart findBookCart(String email,String bookId);
}
