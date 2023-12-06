package com.ourbook.shop.dto.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/** 기본적인 책 정보 조회 DTO **/
@ToString
@Getter
@Setter
public class Book {

    private String bookId;

    private String bookName;

    private BigDecimal bookPrice;

    private String bookImgUrl;

    private String bookExplain;


    public Book(String bookId, String bookName, BigDecimal bookPrice,String bookImgUrl, String bookExplain) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookImgUrl=bookImgUrl;
        this.bookExplain=bookExplain;
    }
}
