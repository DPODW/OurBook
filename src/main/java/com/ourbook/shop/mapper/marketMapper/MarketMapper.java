package com.ourbook.shop.mapper.marketMapper;


import com.ourbook.shop.dto.market.PurchaseRequest;
import com.ourbook.shop.dto.market.SaleBookInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MarketMapper {

    void saleBookInsert(SaleBookInfo saleBookInfo);

    void purchaseRequestInsert(PurchaseRequest purchaseRequest);

    List<SaleBookInfo> findMarketList();

    SaleBookInfo findMarketBook(int number);

    PurchaseRequest findPurchaseRequestHistory();

}
