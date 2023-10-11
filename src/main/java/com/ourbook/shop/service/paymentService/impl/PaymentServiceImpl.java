package com.ourbook.shop.service.paymentService.impl;

import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.mapper.paymentMapper.PaymentMapper;
import com.ourbook.shop.service.paymentService.PaymentService;
import com.ourbook.shop.service.shopService.FindBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;

    private final FindBookService findBookService;

    public PaymentServiceImpl(PaymentMapper paymentMapper, FindBookService findBookService) {
        this.paymentMapper = paymentMapper;
        this.findBookService = findBookService;
    }

    @Override
    public List<PaymentInfo> findPaymentHistory(String email) {
       return paymentMapper.findPaymentHistory(email);
    }

    @Override
    public List<String> findPaymentImg(List<PaymentInfo> paymentHistory) {
        List<String> bookImgIds = paymentHistory.stream()
                .map(PaymentInfo::getBookId)
                .collect(Collectors.toList());

        List<String> bookImages = new ArrayList<>();

        for (String bookId : bookImgIds) {
            String bookImage = findBookService.findBookImg(bookId);
            bookImages.add(bookImage);
        }
        return bookImages;
    }
}
