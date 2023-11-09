package com.ourbook.shop.service.inquiryService.impl;

import com.ourbook.shop.dto.inquiry.InquiryAnswerInfo;
import com.ourbook.shop.dto.inquiry.InquiryInfo;
import com.ourbook.shop.mapper.inquiryMapper.InquiryMapper;
import com.ourbook.shop.service.inquiryService.InquiryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryMapper inquiryMapper;

    public InquiryServiceImpl(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

    @Override
    public void inquirySave(InquiryInfo inquiryInfo) {
        inquiryMapper.inquirySave(inquiryInfo);
    }

    @Override
    public List<InquiryInfo> findInquiryList() {
        List<InquiryInfo> inquiryList = inquiryMapper.findInquiryList();
        inquiryList.stream()
                .map(inquiryInfo -> {
                    inquiryInfo.setSaveTime(extractYearAndMonth(inquiryInfo.getSaveTime()));
                    if(inquiryMapper.findInquiryAnswer(inquiryInfo.getSequence(),inquiryInfo.getInquiryWriter())!=null){
                        inquiryInfo.setInquiryState('o');
                    }else{
                        inquiryInfo.setInquiryState('x');
                    }
                    return inquiryInfo;
                })
                .collect(Collectors.toList());

        return inquiryList;
    }

    @Override
    public InquiryInfo findInquiryContent(int number) {
        InquiryInfo inquiryContent = inquiryMapper.findInquiryContent(number);
        inquiryContent.setSaveTime(extractYearAndMonth(inquiryContent.getSaveTime()));
        return inquiryContent;
    }

    @Override
    public void inquiryAnswerSave(InquiryAnswerInfo inquiryAnswerInfo) {
        if(inquiryMapper.findInquiryAnswer(inquiryAnswerInfo.getInquiryNumber(),inquiryAnswerInfo.getInquiryWriter())!=null){
            inquiryMapper.inquiryAnswerUpdate(inquiryAnswerInfo);
        }else
       inquiryMapper.inquiryAnswerSave(inquiryAnswerInfo);
    }

    @Override
    public InquiryAnswerInfo findInquiryAnswer(int inquiryNumber, String inquiryWriter) {
        InquiryAnswerInfo inquiryAnswer = inquiryMapper.findInquiryAnswer(inquiryNumber, inquiryWriter);
        if (inquiryAnswer!=null){
            inquiryAnswer.setSaveTime(extractYearAndMonth(inquiryAnswer.getSaveTime()));
        }
        return inquiryAnswer;
    }

    private static String extractYearAndMonth(String saveTime){
        return saveTime.substring(0,10);
    }
}
