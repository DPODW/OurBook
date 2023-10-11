package com.ourbook.shop.mapper.paymentMapper;

import com.ourbook.shop.dto.payment.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    void paymentInfoSave(PaymentInfo paymentInfo);

    PaymentInfo findOrderNumber(String orderNumber);


    List<PaymentInfo> findPaymentHistory(String email);
}
