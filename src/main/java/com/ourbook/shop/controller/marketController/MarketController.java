package com.ourbook.shop.controller.marketController;


import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.market.SaleBookInfo;
import com.ourbook.shop.service.marketService.MarketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }



    @PostMapping("/OurBook/market/sale")
    public String SaleBookInsert(@Validated @ModelAttribute SaleBookInfo saleBookInfo, BindingResult bindingResult,
                                 @RequestParam("uploadImg") MultipartFile uploadImg, @AuthenticationPrincipal CustomUserDetail userDetail, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("saleBookInfo", saleBookInfo);
            return "/market/MarketSaleForm";
        }
        saleBookInfo.setUploaderEmail(userDetail.getEmail());
        marketService.SaleBookInsert(saleBookInfo,uploadImg);
        return "redirect:/OurBook/market";
    }
}
