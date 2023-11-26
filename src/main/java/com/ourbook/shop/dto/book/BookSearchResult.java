package com.ourbook.shop.dto.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class BookSearchResult {

    private String searchResult;
    private String category;
    private String uniqueNumber;
    private String searchBookImg;
    private String bookWriter;

    public BookSearchResult(String searchResult, String category, String uniqueNumber,String searchBookImg,String bookWriter) {
        this.searchResult = searchResult;
        this.category = category;
        this.uniqueNumber = uniqueNumber;
        this.searchBookImg = searchBookImg;
        this.bookWriter=bookWriter;
    }
}
