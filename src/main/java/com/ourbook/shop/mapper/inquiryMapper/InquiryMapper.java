package com.ourbook.shop.mapper.inquiryMapper;


import com.ourbook.shop.dto.inquiry.InquiryAnswerInfo;
import com.ourbook.shop.dto.inquiry.InquiryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {

    void inquirySave(InquiryInfo inquiryInfo);

    void inquiryEdit(InquiryInfo inquiryInfo);

    void inquiryDelete(int inquiryNumber);

    List<InquiryInfo> findInquiryList();

    InquiryInfo findInquiryContent(int number);

    void inquiryAnswerSave(InquiryAnswerInfo inquiryAnswerInfo);

    void inquiryAnswerUpdate(InquiryAnswerInfo inquiryAnswerInfo);

    void inquiryAnswerDelete(int inquiryNumber);

    InquiryAnswerInfo findInquiryAnswer(int inquiryNumber);

    List<InquiryInfo> findInquiryHistory(String inquiryWriter);


}
