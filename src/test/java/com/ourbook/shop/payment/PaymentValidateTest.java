package com.ourbook.shop.payment;


import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.mapper.paymentMapper.PaymentMapper;
import com.ourbook.shop.mapper.shopMapper.FindBookMapper;
import com.ourbook.shop.service.additionService.emailService.EmailServiceImpl;
import com.ourbook.shop.service.paymentService.PaymentService;
import com.ourbook.shop.service.paymentService.impl.PaymentServiceImpl;
import com.ourbook.shop.service.shopService.FindBookService;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PaymentValidateTest {

    PaymentService paymentService;

    @Mock
    PaymentMapper paymentMapper;


    FindBookService findBookService;

    @Mock
    FindBookMapper findBookMapper;

    @BeforeEach
    void setup(){
        paymentService = new PaymentServiceImpl(paymentMapper,findBookService,findBookMapper);
    }

    private static PaymentInfo getPaymentInfo() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setReceiverName("testName");
        paymentInfo.setReceiverPhoneNumber("01011111111");
        paymentInfo.setReceiverPostCode("testCode");
        paymentInfo.setReceiverAddress("testAddress");
        return paymentInfo;
    }


    @Test
    @DisplayName("주문 정보가 null 인 경우 검증 테스트")
    void checkPaymentNull(){
        /* given */
        PaymentInfo nullPaymentInfo = new PaymentInfo();

        PaymentInfo paymentInfo = getPaymentInfo();

        /* when ~ then */
        assertThat(paymentService.checkPaymentNull(paymentInfo)).isEqualTo(paymentInfo);
        assertThatThrownBy(()->paymentService.checkPaymentNull(nullPaymentInfo)).isInstanceOf(NullPointerException.class);
    }



    @Test
    @DisplayName("중복된 주문번호 검증 테스트")
    void orderNumberValidate(){
        /* given */
        String testOrderNumber = "testOrderNumber123";

        /* when */
        when(paymentMapper.findOrderNumber(testOrderNumber)).thenReturn(getPaymentInfo());

        /* then */
        assertThatThrownBy(()->paymentService.orderNumberValidate(testOrderNumber)).isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    @DisplayName("요청 금액과 실제 도서 금액 비교 기능 검증")
    void paymentPriceValidate(){
        /* given */
        String testBookId = "testBookId";
        BigDecimal testRequestPrice = new BigDecimal("11.00");
        BigDecimal testBookPrice = new BigDecimal("10.00");

        /* when */
        when(findBookMapper.findBookPrice(testBookId)).thenReturn(testBookPrice);

        /* then */
        assertThatThrownBy(()->paymentService.paymentPriceValidate(testBookId,testRequestPrice)).isInstanceOf(IllegalStateException.class);
    }



}
