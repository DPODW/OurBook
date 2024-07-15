package com.ourbook.shop.service.paymentService.impl;

import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.mapper.paymentMapper.PaymentMapper;
import com.ourbook.shop.mapper.shopMapper.FindBookMapper;
import com.ourbook.shop.service.paymentService.PaymentService;
import com.ourbook.shop.service.shopService.FindBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;

    private final FindBookService findBookService;

    private final FindBookMapper findBookMapper;

    public PaymentServiceImpl(PaymentMapper paymentMapper, FindBookService findBookService, FindBookMapper findBookMapper) {
        this.paymentMapper = paymentMapper;
        this.findBookService = findBookService;
        this.findBookMapper = findBookMapper;
    }

    @Override
    public void paymentInfoDelete(String orderNumber) {
        paymentMapper.paymentInfoDelete(orderNumber);
    }

    @Override
    public List<PaymentInfo> findPaymentHistory(String email) {
        List<PaymentInfo> paymentHistory = paymentMapper.findPaymentHistory(email);
        for (PaymentInfo payment : paymentHistory) {
            BigDecimal price = payment.getPaymentPrice();
            if (price != null) {
                payment.setPaymentPrice(price.setScale(0));
            }
        }
        return paymentHistory;
    }

    @Override
    public String findPaymentResultImg(String bookId) {
        return findBookService.findBookImg(bookId);
    }


    @Override
    public List<String> findPaymentHistoryImg(List<PaymentInfo> paymentHistory) {
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

    @Override
    public PaymentInfo findOrderNumber(String orderNumber) {
        PaymentInfo paymentInfo = paymentMapper.findOrderNumber(orderNumber);
        paymentInfo.setPaymentPrice(paymentInfo.getPaymentPrice().setScale(0));
        return paymentInfo;
    }


    @Override
    public PaymentInfo checkPaymentNull(PaymentInfo paymentInfo){
            if(paymentInfo.getReceiverName().isEmpty() || paymentInfo.getReceiverPhoneNumber().isEmpty()
               || paymentInfo.getReceiverPostCode().isEmpty() || paymentInfo.getReceiverAddress().isEmpty()){
                log.error("주문 정보가 전달되지 않았습니다.");
                throw new NullPointerException("주문 정보가 전달되지 않았습니다.");
            }else
                return paymentInfo;
        }

    @Override
    public void orderNumberValidate(String orderNumber) {
        PaymentInfo paymentHistory = paymentMapper.findOrderNumber(orderNumber);
        if(paymentHistory != null){
            log.error("중복된 주문 번호가 존재합니다.");
            throw new DuplicateKeyException("중복된 주문 번호.");
        }
    }

    @Override
    public void paymentPriceValidate(String bookId,BigDecimal paymentPrice) {
        BigDecimal bookPrice = findBookMapper.findBookPrice(bookId);
        if(!paymentPrice.equals(bookPrice.setScale(0))){
            log.error("요청 금액과 실제 도서 금액이 다릅니다.");
            throw new IllegalStateException("요청 금액 다름");
        }
    }
}
