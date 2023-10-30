package com.ourbook.shop.controller.marketController;


import com.ourbook.shop.dto.market.SaleBookInfo;
import com.ourbook.shop.service.marketService.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("saleBookInfo",saleBookInfo);
        return "/market/MarketSaleForm";
    }

    @GetMapping("/OurBook/market/sale/info/{number}")
    public String marketSaleInfoView(@PathVariable int number,Model model){
        SaleBookInfo saleBookInfo = marketService.findMarketBook(number);
        saleBookInfo.setSaleBookPrice(saleBookInfo.getSaleBookPrice().setScale(0));
        log.info("{}",saleBookInfo);
        log.info("{}",saleBookInfo.getSaleImg());
        model.addAttribute("saleBookInfo", saleBookInfo);
        return "market/MarketSaleInfo";
    }

}
