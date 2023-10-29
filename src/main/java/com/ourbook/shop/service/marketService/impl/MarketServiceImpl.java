package com.ourbook.shop.service.marketService.impl;

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

    @Value("${findFile.dir}")
    private String findFileDir;

    @Override
    public void SaleBookInsert(SaleBookInfo saleBookInfo, MultipartFile uploadImg) throws IOException {
        String saleImgUrl = fileUploadService.uploadFile(uploadImg);
        saleBookInfo.setSaleImg(findFileDir+saleImgUrl);
        marketMapper.saleBookInsert(saleBookInfo);
    }

    @Override
    public List<SaleBookInfo> findMarketList() {
        List<SaleBookInfo> marketList = marketMapper.findMarketList();
            marketList.stream()
                .map(saleBookInfo -> {
                    String time = saleBookInfo.getSaveTime();
                    saleBookInfo.setSaveTime(time.substring(5,10));
                    return saleBookInfo;
                })
                    .collect(Collectors.toList());
            log.info("{}",marketList);
        return marketList;
    }

    @Override
    public SaleBookInfo findMarketBook(int number) {
        return marketMapper.findMarketBook(number);
    }
}
