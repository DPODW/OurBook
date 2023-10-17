package com.ourbook.shop.dto.inquiry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class PublishProposalInfo {

    private String publishBookName;
    private String publishWriter;
    private String publishBookStory;
    private String publishEtc;
    private String publishBookPage;
    private String publishBookCount;


    public PublishProposalInfo(String publishBookName, String publishWriter, String publishBookStory, String publishEtc, String publishBookPage, String publishBookCount) {
        this.publishBookName = publishBookName;
        this.publishWriter = publishWriter;
        this.publishBookStory = publishBookStory;
        this.publishEtc = publishEtc;
        this.publishBookPage = publishBookPage;
        this.publishBookCount = publishBookCount;
    }
}
