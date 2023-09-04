package com.ourbook.shop.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Book {

    private String bookId;

    private String bookName;

    private BigDecimal bookPrice;

    private String bookImgUrl;

    public Book(String bookId, String bookName, BigDecimal bookPrice,String bookImgUrl) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookImgUrl=bookImgUrl;
    }
}
