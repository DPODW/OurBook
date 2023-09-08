package com.ourbook.shop.dto.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class BookCart {



    private String email;

    private String bookId;

    private int bookCount;


    public BookCart(String email, String bookId, int bookCount) {
        this.email = email;
        this.bookId = bookId;
        this.bookCount = bookCount;

    }
}
