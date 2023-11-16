package com.ourbook.shop.dto.inquiry;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@ToString
@Setter
@Getter
public class InquiryAnswerInfo {

    private String inquiryWriter;
    private int inquiryNumber;
    private String adminName;
    private String adminContent;
    @Nullable
    private String saveTime;

    public InquiryAnswerInfo(String inquiryWriter, int inquiryNumber, String adminName, String adminContent, String saveTime) {
        this.inquiryWriter = inquiryWriter;
        this.inquiryNumber = inquiryNumber;
        this.adminName = adminName;
        this.adminContent = adminContent;
        this.saveTime = saveTime;
    }
}
