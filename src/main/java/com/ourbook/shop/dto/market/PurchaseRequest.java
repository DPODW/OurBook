package com.ourbook.shop.dto.market;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;


@ToString
@Setter
@Getter
public class PurchaseRequest {

    private String uploaderEmail;
    private String uploaderName;
    private String receiverEmail;
    private String receiverName;
    private String saleBookName;

    public PurchaseRequest(String uploaderEmail, String uploaderName, String receiverEmail, String receiverName, String saleBookName) {
        this.uploaderEmail = uploaderEmail;
        this.uploaderName = uploaderName;
        this.receiverEmail = receiverEmail;
        this.receiverName = receiverName;
        this.saleBookName = saleBookName;
    }
}
