package com.ourbook.shop.mapper.inquiryMapper;


import com.ourbook.shop.dto.inquiry.InquiryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {

    void inquirySave(InquiryInfo inquiryInfo);

    List<InquiryInfo> findInquiryList();
}
