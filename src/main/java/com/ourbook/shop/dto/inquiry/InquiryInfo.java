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
    private String inquiryEmail;

    @NotBlank
    @Length(min=2, max= 15)
    private String inquiryName;

    @NotBlank
    @Length(min=10, max= 300)
    private String inquiryContent;

    @Nullable
    private String saveTime;

    @Nullable
    private int sequence;

    private char inquiryState;
    //inquiryState 는 DB랑 상관 없는 객체 (답변 여부) 라서 생성자에 넣지 않음.
    //DB에 컬럼도 존재하지 않기 때문에, 생성자에 넣으면 예외 발생 (DB 컬럼 수 보다 초과해서 바인딩 시도)


    public InquiryInfo(String inquiryType, String inquiryWriter,String inquiryEmail, String inquiryName, String inquiryContent, @Nullable String saveTime, @Nullable int sequence) {
        this.inquiryType = inquiryType;
        this.inquiryWriter = inquiryWriter;
        this.inquiryEmail = inquiryEmail;
        this.inquiryName = inquiryName;
        this.inquiryContent = inquiryContent;
        this.saveTime = saveTime;
        this.sequence = sequence;
    }



}
