package com.ourbook.shop.dto.PayMent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PaymentCancel {

    private String accessToken;

    private String imp_uid;

    private String reason;

    private int paymentPrice;

    public PaymentCancel(String accessToken, String imp_uid, String reason, int paymentPrice) {
        this.accessToken = accessToken;
        this.imp_uid = imp_uid;
        this.reason = reason;
        this.paymentPrice = paymentPrice;
    }
}
