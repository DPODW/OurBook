package com.ourbook.shop.service.inquiryService;
import com.ourbook.shop.dto.inquiry.InquiryAnswerInfo;
import com.ourbook.shop.dto.inquiry.InquiryInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InquiryService {

     void inquirySave(InquiryInfo inquiryInfo);

     void inquiryEdit(InquiryInfo inquiryInfo);

     void inquiryDelete(int inquiryNumber);

     List<InquiryInfo> findInquiryList();

     InquiryInfo findInquiryContent(int number);

     void inquiryAnswerSave(InquiryAnswerInfo inquiryAnswerInfo);

     void inquiryAnswerDelete(int inquiryNumber);

     InquiryAnswerInfo findInquiryAnswer(int inquiryNumber);

     List<InquiryInfo> findInquiryHistory(String inquiryWriter);
}
