package com.ourbook.shop.mapper.shopMapper;

import com.ourbook.shop.dto.book.BookCartSave;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BookCartMapper {

    void insertBookCart(BookCartSave bookCartSave);

    void updateBookCount(BookCartSave bookCartSave);

    BookCartSave findBookCart(String email, String bookId);

    List<Map<String, Object>> findCartToEmail(String email);


}
