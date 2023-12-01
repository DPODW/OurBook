package com.ourbook.shop.dto.library;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class LibraryInfo {
    private String title;

    private String link;

    private String category;

    private String address;

    private String roadAddress;


    public LibraryInfo(String title, String link, String category, String address, String roadAddress) {
        this.title = title;
        this.link = link;
        this.category = category;
        this.address = address;
        this.roadAddress = roadAddress;
    }

}
