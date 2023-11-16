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
     /** findInquiryAnswer 는 등록된 답변이 없는 상태에서 접근할시 NULL 을 반환함. NULL 상황에 따른 처리(조건) 가 필요함 **/

     List<InquiryInfo> findInquiryHistory(String inquiryWriter);
}
