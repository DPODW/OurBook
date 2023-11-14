package com.ourbook.shop.controller.marketController;


import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.inquiry.InquiryInfo;
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
    public String marketSaleView(SaleBookInfo saleBookInfo, Model model, HttpServletRequest request, @AuthenticationPrincipal CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
        if ((userDetail == null || !userDetail.getAuthorities().iterator().next().toString().equals("SELLER")) &&
                (naverMember == null || !naverMember.getRole().equals("SELLER"))) {
            return "redirect:/OurBook";
        }
        if(naverMember!=null){
            model.addAttribute("uploaderName",naverMember.getName());
        }else{
            model.addAttribute("uploaderName",userDetail.getName());
        }
        model.addAttribute("saleBookInfo",saleBookInfo);
        return "/market/MarketSaleForm";
    }


    @GetMapping("/OurBook/market/sale/info/{number}")
    public String marketSaleInfoView(@PathVariable int number,Model model){
        SaleBookInfo saleBookInfo = marketService.findMarketBook(number);
        model.addAttribute("saleBookInfo", saleBookInfo);
        return "market/MarketSaleInfo";
    }


    @GetMapping("/OurBook/market/purchase/request/history")
    public String PurchaseRequestHistory(HttpServletRequest request,@AuthenticationPrincipal CustomUserDetail userDetail,Model model){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
           SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            List<PurchaseRequest> purchaseRequestHistory = marketService.findPurchaseRequestHistory(naverMember.getEmail());
            model.addAttribute("purchaseRequestHistorys",purchaseRequestHistory);
        }else{
            List<PurchaseRequest> purchaseRequestHistory = marketService.findPurchaseRequestHistory(userDetail.getEmail());
            model.addAttribute("purchaseRequestHistorys",purchaseRequestHistory);
        }
        return "market/PurchaseRequestHistory";
    }



}
