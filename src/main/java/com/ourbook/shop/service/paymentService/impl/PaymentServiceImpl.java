package com.ourbook.shop.service.paymentService.impl;

import com.ourbook.shop.dto.PayMent.PaymentInfo;
import com.ourbook.shop.mapper.paymentMapper.PaymentMapper;
import com.ourbook.shop.service.paymentService.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    @Override
    public void paymentInfoSave(PaymentInfo paymentInfo) {
       paymentInfo.setOrderNumber("orderNumber");
       paymentInfo.setBuyerEmail("email");
        log.info("{}",paymentInfo);
       paymentMapper.paymentInfoSave(paymentInfo);

    }
}
