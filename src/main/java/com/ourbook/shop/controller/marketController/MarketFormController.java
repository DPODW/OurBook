package com.ourbook.shop.controller.marketController;


import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
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
        saleBookInfo.setSaleBookPrice(saleBookInfo.getSaleBookPrice().setScale(0));
        model.addAttribute("saleBookInfo", saleBookInfo);
        return "market/MarketSaleInfo";
    }


    private boolean checkSellerRole(CustomUserDetail userDetail, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        SessionUser naver = (SessionUser) session.getAttribute("NAVER");
        if ((userDetail == null || !userDetail.getAuthorities().iterator().next().toString().equals("SELLER")) &&
                (naver == null || !naver.getRole().equals("SELLER"))) {
            return false;
        }
        return true;
        //userDetail , naver 객체는 동시에 존재할 수 없어서, null 조건을 달지 않을시 존재하지 않는 객체에서 오류가 발생함. 고로 ==null 조건 필요
    }
}
