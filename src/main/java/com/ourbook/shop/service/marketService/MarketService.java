package com.ourbook.shop.service.marketService;


import com.ourbook.shop.dto.market.SaleBookInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MarketService {

    void SaleBookInsert(SaleBookInfo saleBookInfo);

    List<SaleBookInfo> findMarketList();
}
