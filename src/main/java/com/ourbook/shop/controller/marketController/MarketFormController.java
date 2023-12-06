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
        return "/market/MarketList";
    }


    @GetMapping("/market/sale")
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

    @GetMapping("/market/sale/{marketNumber}")
    public String marketSaleEditView(@PathVariable int marketNumber,Model model){
        SaleBookInfo saleBookInfo = marketService.findMarketBook(marketNumber);
        model.addAttribute("saleBookInfo",saleBookInfo);
        return "market/MarketSaleEdit";
    }



    @GetMapping("/market/sale/info/{number}")
    public String marketSaleInfoView(@PathVariable int number,@AuthenticationPrincipal CustomUserDetail userDetail,Model model){
        SaleBookInfo saleBookInfo = marketService.findMarketBook(number);
        if(userDetail!=null){
            model.addAttribute("sellerId",userDetail.getUsername());
        }
        model.addAttribute("saleBookInfo", saleBookInfo);
        return "market/MarketSaleInfo";
    }


    @GetMapping("/market/purchase/request/history")
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
