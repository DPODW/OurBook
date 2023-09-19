package com.ourbook.shop.service.paymentService.impl;

import com.ourbook.shop.config.exception.PaymentFailException;
import com.ourbook.shop.dto.PayMent.PaymentInfo;
import com.ourbook.shop.mapper.paymentMapper.PaymentMapper;
import com.ourbook.shop.mapper.shopMapper.FindBookMapper;
import com.ourbook.shop.service.paymentService.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
       try {
           paymentMapper.paymentInfoSave(paymentInfo);
       }catch (Exception ex){
           throw new PaymentFailException();
       }
    }

    @Override
    public void paymentValidate(String orderNumber) {
        PaymentInfo paymentHistory = paymentMapper.findOrderNumber(orderNumber);
        if(paymentHistory != null){
            throw new PaymentFailException();
        }else{
            log.info("결제 검증 완료");
        }
    }

}
