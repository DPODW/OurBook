package com.ourbook.shop.service.inquiryService;
import com.ourbook.shop.dto.inquiry.InquiryInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InquiryService {

     void inquirySave(InquiryInfo inquiryInfo);

     List<InquiryInfo> findInquiryList();

}
