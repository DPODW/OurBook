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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @PostMapping("/OurBook/market/sale")
    public String SaleBookInsert(@Validated @ModelAttribute SaleBookInfo saleBookInfo, BindingResult bindingResult, HttpServletRequest request,
                                 @AuthenticationPrincipal CustomUserDetail userDetail, Model model ){
        HttpSession session = request.getSession(false);
        if (bindingResult.hasErrors()) {
            model.addAttribute("saleBookInfo", saleBookInfo);
            return "/market/MarketSaleForm";
        }

        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            saleBookInfo.setUploaderEmail(naverMember.getEmail());
        }else{
            saleBookInfo.setUploaderEmail(userDetail.getEmail());
        }
        marketService.SaleBookInsert(saleBookInfo);
        return "redirect:/OurBook/market";
    }
}
