package com.ourbook.shop.service.marketService;


import com.ourbook.shop.dto.market.SaleBookInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface MarketService {

    void SaleBookInsert(SaleBookInfo saleBookInfo, MultipartFile uploadImg) throws IOException;

    List<SaleBookInfo> findMarketList();

    SaleBookInfo findMarketBook(int number);
}
