package com.ourbook.shop.controller.marketController;


import com.ourbook.shop.config.auth.session.NaverMember;
import com.ourbook.shop.config.auth.session.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.market.PurchaseRequest;
import com.ourbook.shop.dto.market.SaleBookInfo;
import com.ourbook.shop.service.marketService.MarketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequestMapping("/OurBook")
@Controller
public class MarketFormController {

    private final MarketService marketService;

    public MarketFormController(MarketService marketService) {
        this.marketService = marketService;
    }


    @GetMapping("/market")
    public String marketListView(Model model){
        List<SaleBookInfo> marketList = marketService.findMarketList();

        model.addAttribute("marketList",marketList);
        return "market/marketList";
    }


    @GetMapping("/market/sale")
    public String marketSaleView(SaleBookInfo saleBookInfo, @NaverMember SessionUser sessionUser, @AuthenticationPrincipal CustomUserDetail userDetail, Model model){
        if ((userDetail == null || !userDetail.getAuthorities().iterator().next().toString().equals("SELLER")) &&
                (sessionUser == null || !sessionUser.getRole().equals("SELLER"))) {
            return "redirect:/OurBook";
        }
        if(sessionUser!=null){
            model.addAttribute("uploaderName",sessionUser.getName());
        }else{
            model.addAttribute("uploaderName",userDetail.getName());
        }
        model.addAttribute("saleBookInfo",saleBookInfo);
        return "market/marketSaleForm";
    }

    @GetMapping("/market/sale/edit/{marketNumber}")
    public String marketSaleEditView(@PathVariable int marketNumber,Model model){
        SaleBookInfo saleBookInfo = marketService.findMarketBook(marketNumber);
        model.addAttribute("saleBookInfo",saleBookInfo);
        return "market/marketSaleEdit";
    }



    @GetMapping("/market/sale/info/{number}")
    public String marketSaleInfoView(@PathVariable int number,@AuthenticationPrincipal CustomUserDetail userDetail,Model model){
        SaleBookInfo saleBookInfo = marketService.findMarketBook(number);
        model.addAttribute("saleBookInfo", saleBookInfo);
        return "market/marketSaleInfo";
    }


    @GetMapping("/market/purchase/request/history")
    public String PurchaseRequestHistory(@NaverMember SessionUser sessionUser,@AuthenticationPrincipal CustomUserDetail userDetail,Model model){
        if(sessionUser!=null){
            List<PurchaseRequest> purchaseRequestHistory = marketService.findPurchaseRequestHistory(sessionUser.getEmail());
            model.addAttribute("purchaseRequestHistorys",purchaseRequestHistory);
        }else{
            List<PurchaseRequest> purchaseRequestHistory = marketService.findPurchaseRequestHistory(userDetail.getEmail());
            model.addAttribute("purchaseRequestHistorys",purchaseRequestHistory);
        }
        return "market/purchaseRequestHistory";
    }



}
