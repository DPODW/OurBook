package com.ourbook.shop.dto.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** 사용자가 선택한 상품을 장바구니 DB 에 저장하는 DTO  **/
@Setter
@Getter
@ToString
public class BookCartSave {
    private String email;

    private String bookId;

    private int bookCount;


    public BookCartSave(String email, String bookId, int bookCount) {
        this.email = email;
        this.bookId = bookId;
        this.bookCount = bookCount;

    }
}
