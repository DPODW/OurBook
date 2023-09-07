package com.ourbook.shop.dto.book;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BookCart {

    private String email;

    private String bookId;

    private String bookCount;

    public BookCart(String email, String bookId, String bookCount) {
        this.email = email;
        this.bookId = bookId;
        this.bookCount = bookCount;
    }
}
