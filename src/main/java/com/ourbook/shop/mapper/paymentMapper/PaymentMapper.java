package com.ourbook.shop.mapper.paymentMapper;

import com.ourbook.shop.dto.PayMent.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    void paymentInfoSave(PaymentInfo paymentInfo);

    PaymentInfo findOrderNumber(String orderNumber);
}
