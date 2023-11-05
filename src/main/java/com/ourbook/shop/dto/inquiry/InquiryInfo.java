package com.ourbook.shop.dto.inquiry;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@ToString
@Setter
@Getter
public class InquiryInfo {

    private String inquiryType;

    private String inquiryWriter;

    @NotBlank
    @Length(min=2, max= 15)
    private String inquiryName;

    @NotBlank
    @Length(min=10, max= 50)
    private String inquiryContent;

    @Nullable
    private String saveTime;

    @Nullable
    private int sequence;

    public InquiryInfo(String inquiryType, String inquiryWriter, String inquiryName, String inquiryContent, @Nullable String saveTime, @Nullable int sequence) {
        this.inquiryType = inquiryType;
        this.inquiryWriter = inquiryWriter;
        this.inquiryName = inquiryName;
        this.inquiryContent = inquiryContent;
        this.saveTime = saveTime;
        this.sequence = sequence;
    }



}
