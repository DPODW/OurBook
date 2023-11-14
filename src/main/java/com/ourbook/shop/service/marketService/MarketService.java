package com.ourbook.shop.service.marketService;


import com.ourbook.shop.dto.market.PurchaseRequest;
import com.ourbook.shop.dto.market.SaleBookInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface MarketService {

    void saleBookInsert(SaleBookInfo saleBookInfo, MultipartFile uploadImg) throws IOException;

    void purchaseRequestInsert(PurchaseRequest purchaseRequest);

    List<SaleBookInfo> findMarketList();

    SaleBookInfo findMarketBook(int number);

    List<PurchaseRequest> findPurchaseRequestHistory(String receiverEmail);

    int findPurchaseRequestCount(String saleBookName);
}
