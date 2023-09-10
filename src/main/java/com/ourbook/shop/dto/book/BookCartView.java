package com.ourbook.shop.dto.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/** 사용자 장바구니 결과 출력을 위한 DTO [DB와 교류하지 않음] **/
@ToString
@Setter
@Getter
public class BookCartView {
    private String bookId;

    private String bookName;

    private BigDecimal bookPrice;

    private String bookImgUrl;

    private String bookExplan;

    private Integer bookCount;


    public BookCartView(String bookId, String bookName, BigDecimal bookPrice,String bookImgUrl, String bookExplan,Integer bookCount) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookImgUrl=bookImgUrl;
        this.bookExplan=bookExplan;
        this.bookCount=bookCount;
    }

}
