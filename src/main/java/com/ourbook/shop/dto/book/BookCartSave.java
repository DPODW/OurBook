package com.ourbook.shop.dto.book;

import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

/** 사용자가 선택한 상품을 장바구니 DB 에 저장하는 DTO  **/
@Setter
@Getter
@ToString
public class BookCartSave {
    private String email;

    private String bookId;

    private int bookCount;

    @Nullable
    private int sequence;


    public BookCartSave(String email, String bookId, int bookCount,int sequence) {
        this.email = email;
        this.bookId = bookId;
        this.bookCount = bookCount;
        this.sequence=sequence;
    }
}
