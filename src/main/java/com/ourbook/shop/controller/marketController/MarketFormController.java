package com.ourbook.shop.controller.marketController;


import com.ourbook.shop.dto.market.SaleBookInfo;
import com.ourbook.shop.service.marketService.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class MarketFormController {

    private final MarketService marketService;

    public MarketFormController(MarketService marketService) {
        this.marketService = marketService;
    }


    @GetMapping("/OurBook/market")
    public String marketListView(Model model){
        List<SaleBookInfo> marketList = marketService.findMarketList();

        model.addAttribute("marketList",marketList);
        return "/market/MarketList";
    }


    @GetMapping("/OurBook/market/sale")
    public String marketSaleView(SaleBookInfo saleBookInfo,Model model){
        model.addAttribute("SaleBookInfo",saleBookInfo);
        log.info("{}",saleBookInfo);
        return "/market/MarketSaleForm";
    }
}
