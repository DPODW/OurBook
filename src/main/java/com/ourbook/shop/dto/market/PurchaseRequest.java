package com.ourbook.shop.dto.market;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;
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
    @Nullable
    private String saveTime;
    private int sequence;
    /** sequence 는 DB 에서 AUTO INCREMENT 로 생성되는 경우가 많으나, PurchaseRequest(구매 요청 정보를 저장하는 DTO) 에서는
       도서 시장에 저장되어있는 시퀀스 값으로 저장해야함. -> 고로 해당 시퀀스는 도서 시장의 판매 도서의 번호를 받아오기 때문에 일반 필드와 동일 **/

    /** 도서시장 시퀀스를, 도서시장 - 구매요청내역 에도 동일하게 저장하는 이유는 -> 도서시장 제품에 접근할때, 해당 시퀀스를 가져와서 DB에 검색 , 반환된 것을 보여주는
     *  방식임. 만약 도서시장 시퀀스와 도서시장 - 구매요청내역의 시퀀스가 다르면, 구매요청 내역에서 해당 제품으로 바로 접근이 불가능함.
     *  EX) 도서시장 의 A 책은 시퀀스가 4 인데 (아래에 책 3권이 이미 등재되었기 때문) 회원의 구매요청 내역의 시퀀스는 1로 되어있음. (처음 구매요청이라)
     *  이렇게 되면 -> 4로 검색해야 A 책이 검색되는데 1로 검색되기 때문에 에러가 발생하게 됌. . .
     *  즉 => 구매요청 내역의 시퀀스는 도서시장에 등록된 번호를 가져오는 방식으로 구현됌.
     *  **/


    public PurchaseRequest(String uploaderEmail, String uploaderName, String receiverEmail, String receiverName, String saleBookName,String saveTime,int sequence) {
        this.uploaderEmail = uploaderEmail;
        this.uploaderName = uploaderName;
        this.receiverEmail = receiverEmail;
        this.receiverName = receiverName;
        this.saleBookName = saleBookName;
        this.saveTime=saveTime;
        this.sequence=sequence;
    }
}
