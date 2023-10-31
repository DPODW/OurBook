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
    /** Todo: AWS S3 로 구현 시, 수정 필요한 코드 위치 **/
    private final MarketMapper marketMapper;

    private final FileUploadService fileUploadService;

    public MarketServiceImpl(MarketMapper marketMapper, FileUploadService fileUploadService) {
        this.marketMapper = marketMapper;
        this.fileUploadService = fileUploadService;
    }
    @Override
    public void SaleBookInsert(SaleBookInfo saleBookInfo, MultipartFile uploadImg) throws IOException {
        String uploadImgUrl = fileUploadService.uploadImgFile(uploadImg);
        saleBookInfo.setSaleImg(uploadImgUrl); //실제 DB 에 저장되는건 AWS URL 이니까 변수명은 뒤에 URL 이 붙는게 좋다
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

        return marketList;
    }

    @Override
    public SaleBookInfo findMarketBook(int number) {
        return marketMapper.findMarketBook(number);
    }
}
