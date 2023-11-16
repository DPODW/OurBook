package com.ourbook.shop.service.inquiryService.impl;

import com.ourbook.shop.dto.inquiry.InquiryAnswerInfo;
import com.ourbook.shop.dto.inquiry.InquiryInfo;
import com.ourbook.shop.mapper.inquiryMapper.InquiryMapper;
import com.ourbook.shop.service.inquiryService.InquiryService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
    public void inquiryEdit(InquiryInfo inquiryInfo) {
        inquiryMapper.inquiryEdit(inquiryInfo);
    }

    @Override
    public void inquiryDelete(int inquiryNumber) {
        inquiryMapper.inquiryDelete(inquiryNumber);
        if(inquiryMapper.findInquiryAnswer(inquiryNumber)!=null){
            inquiryMapper.inquiryAnswerDelete(inquiryNumber);
        }
    }

    @Override
    public List<InquiryInfo> findInquiryList() {
        List<InquiryInfo> inquiryList = inquiryMapper.findInquiryList();
        getInquiryList(inquiryList);
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
        if(inquiryMapper.findInquiryAnswer(inquiryAnswerInfo.getInquiryNumber())!=null){
            inquiryMapper.inquiryAnswerUpdate(inquiryAnswerInfo);
        }else
       inquiryMapper.inquiryAnswerSave(inquiryAnswerInfo);
    }

    @Override
    public void inquiryAnswerDelete(int inquiryNumber) {
        inquiryMapper.inquiryAnswerDelete(inquiryNumber);
    }

    @Override
    public InquiryAnswerInfo findInquiryAnswer(int inquiryNumber) {
        InquiryAnswerInfo inquiryAnswer = inquiryMapper.findInquiryAnswer(inquiryNumber);
        if (inquiryAnswer!=null){
            inquiryAnswer.setSaveTime(extractYearAndMonth(inquiryAnswer.getSaveTime()));
        }
        return inquiryAnswer;
    }

    @Override
    public List<InquiryInfo> findInquiryHistory(String inquiryWriter) {
        List<InquiryInfo> inquiryHistory = inquiryMapper.findInquiryHistory(inquiryWriter);
        getInquiryList(inquiryHistory);
        return inquiryHistory;

    }


    @NotNull
    private List<InquiryInfo> getInquiryList(List<InquiryInfo> inquiryHistory) {
        return inquiryHistory.stream()
                .map(inquiryInfo -> {
                    inquiryInfo.setSaveTime(extractYearAndMonth(inquiryInfo.getSaveTime()));
                    if (inquiryMapper.findInquiryAnswer(inquiryInfo.getSequence()) != null) {
                        inquiryInfo.setInquiryState('o');
                    } else {
                        inquiryInfo.setInquiryState('x');
                    }
                    return inquiryInfo;
                })
                .collect(Collectors.toList());
    }


    private static String extractYearAndMonth(String saveTime){
        return saveTime.substring(0,10);
    }
}
