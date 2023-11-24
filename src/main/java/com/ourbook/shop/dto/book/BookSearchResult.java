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

    public BookSearchResult(String searchResult, String category, String uniqueNumber) {
        this.searchResult = searchResult;
        this.category = category;
        this.uniqueNumber = uniqueNumber;
    }
}
