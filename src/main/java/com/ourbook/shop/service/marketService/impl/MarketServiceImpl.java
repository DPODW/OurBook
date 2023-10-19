package com.ourbook.shop.service.marketService.impl;

import com.ourbook.shop.dto.market.SaleBookInfo;
import com.ourbook.shop.mapper.marketMapper.MarketMapper;
import com.ourbook.shop.service.marketService.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MarketServiceImpl implements MarketService {

    private final MarketMapper marketMapper;

    public MarketServiceImpl(MarketMapper marketMapper) {
        this.marketMapper = marketMapper;
    }

    @Override
    public void SaleBookInsert(SaleBookInfo saleBookInfo) {
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
}
