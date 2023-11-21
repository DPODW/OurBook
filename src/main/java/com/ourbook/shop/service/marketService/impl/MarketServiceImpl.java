package com.ourbook.shop.service.marketService.impl;

import com.ourbook.shop.dto.market.PurchaseRequest;
import com.ourbook.shop.dto.market.SaleBookInfo;
import com.ourbook.shop.mapper.marketMapper.MarketMapper;
import com.ourbook.shop.service.additionService.fileUploadService.FileUploadService;
import com.ourbook.shop.service.marketService.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MarketServiceImpl implements MarketService {
    private final MarketMapper marketMapper;

    private final FileUploadService fileUploadService;

    public MarketServiceImpl(MarketMapper marketMapper, FileUploadService fileUploadService) {
        this.marketMapper = marketMapper;
        this.fileUploadService = fileUploadService;
    }
    @Override
    public void saleBookInsert(SaleBookInfo saleBookInfo, MultipartFile uploadImg) throws IOException {
        String uploadImgUrl = fileUploadService.uploadImgFile(uploadImg);
        saleBookInfo.setSaleImg(uploadImgUrl); //실제 DB 에 저장되는건 AWS URL 이니까 변수명은 뒤에 URL 이 붙는게 좋다
        marketMapper.saleBookInsert(saleBookInfo);
    }

    @Override
    public void saleBookDelete(int marketNumber) throws IOException {
        fileUploadService.deleteImgFile(marketNumber);
        marketMapper.saleBookDelete(marketNumber);
    }

    @Override
    public void saleBookEdit(SaleBookInfo saleBookInfo,MultipartFile uploadImg) throws IOException {
        fileUploadService.deleteImgFile(saleBookInfo.getSequence());

        String uploadImgUrl = fileUploadService.uploadImgFile(uploadImg);
        saleBookInfo.setSaleImg(uploadImgUrl);
        marketMapper.saleBookEdit(saleBookInfo);
    }

    @Override
    public void purchaseRequestInsert(PurchaseRequest purchaseRequest) {
        marketMapper.purchaseRequestInsert(purchaseRequest);
    }

    @Override
    public List<SaleBookInfo> findMarketList() {
        List<SaleBookInfo> marketList = marketMapper.findMarketList();
            marketList.stream()
                .map(saleBookInfo -> {
                    saleBookInfo.setSaveTime(extractYearAndMonth(saleBookInfo.getSaveTime()));
                    saleBookInfo.setPurchaseRequestCount(findPurchaseRequestCount(saleBookInfo.getSequence()));
                    return saleBookInfo;
                })
                    .collect(Collectors.toList());

        return marketList;
    }

    @Override
    public SaleBookInfo findMarketBook(int number) {
        SaleBookInfo marketBook = marketMapper.findMarketBook(number);
        marketBook.setSaveTime(extractYearAndMonth(marketBook.getSaveTime()));
        marketBook.setSaleBookPrice(marketBook.getSaleBookPrice().setScale(0));
        marketBook.setPurchaseRequestCount(findPurchaseRequestCount(marketBook.getSequence()));
        return marketBook;
    }


    @Override
    public List<PurchaseRequest> findPurchaseRequestHistory(String receiverEmail) {
        List<PurchaseRequest> purchaseRequestHistory = marketMapper.findPurchaseRequestHistory(receiverEmail);
        purchaseRequestHistory.stream()
                .map(purchaseRequest -> {
                    purchaseRequest.setSaveTime(extractYearAndMonth(purchaseRequest.getSaveTime()));
                    return purchaseRequest;
                })
                .collect(Collectors.toList());
        return purchaseRequestHistory;
    }

    @Override
    public int findPurchaseRequestCount(int purchaseRequestNumber) {
       return marketMapper.findPurchaseRequestCount(purchaseRequestNumber);
    }

    private static String extractYearAndMonth(String saveTime){
        return saveTime.substring(0,10);
    }
}
