package com.ourbook.shop.dto.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
public class Book {

    private String bookId;

    private String bookName;

    private BigDecimal bookPrice;

    private String bookImgUrl;

    private String bookPlane;

    public Book(String bookId, String bookName, BigDecimal bookPrice,String bookImgUrl, String bookPlane) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookImgUrl=bookImgUrl;
        this.bookPlane=bookPlane;
    }
}
