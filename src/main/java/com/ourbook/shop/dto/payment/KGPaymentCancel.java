package com.ourbook.shop.dto.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class KGPaymentCancel {
    /** IamPort(포트원)사의 KG 이니시스 결제 취소시, 사용되는 DTO 클래스 **/
    private String accessToken;

    private String imp_uid;

    private String reason;

    private int paymentPrice;

    public KGPaymentCancel(String accessToken, String imp_uid, String reason, int paymentPrice) {
        this.accessToken = accessToken;
        this.imp_uid = imp_uid;
        this.reason = reason;
        this.paymentPrice = paymentPrice;
    }
}
