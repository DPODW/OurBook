package com.ourbook.shop.controller.marketController;


import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.market.PurchaseRequest;
import com.ourbook.shop.dto.market.SaleBookInfo;
import com.ourbook.shop.service.additionService.emailService.EmailService;
import com.ourbook.shop.service.marketService.MarketService;
import jakarta.mail.MessagingException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
public class MarketController {

    private final MarketService marketService;

    private final EmailService emailService;

    public MarketController(MarketService marketService, EmailService emailService) {
        this.marketService = marketService;
        this.emailService = emailService;
    }


    @PostMapping("/OurBook/market/sale")
    public String SaleBookInsert(@Validated @ModelAttribute SaleBookInfo saleBookInfo, BindingResult bindingResult,
                                 @RequestParam("uploadImg") MultipartFile uploadImg, @AuthenticationPrincipal CustomUserDetail userDetail, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("saleBookInfo", saleBookInfo);
            return "/market/MarketSaleForm";
        }
        saleBookInfo.setUploaderEmail(userDetail.getEmail());
        marketService.saleBookInsert(saleBookInfo,uploadImg);
        return "redirect:/OurBook/market";
    }

    @PostMapping("/OurBook/market/purchase/request/warning")
    public String BookPurchaseRequestWarn(@ModelAttribute PurchaseRequest purchaseRequest, HttpServletRequest request, @AuthenticationPrincipal CustomUserDetail userDetail,
                                          Model model){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
          SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
          purchaseRequest.setReceiverEmail(naverMember.getEmail());
          purchaseRequest.setReceiverName(naverMember.getName());
        }else{
          purchaseRequest.setReceiverEmail(userDetail.getEmail());
          purchaseRequest.setReceiverName(userDetail.getName());
        }
        model.addAttribute("purchaseRequest",purchaseRequest);
        return "market/MarketSaleWarning";
    }



    @PostMapping("/OurBook/market/purchase/request")
    public String BookPurchaseRequest(@ModelAttribute PurchaseRequest purchaseRequest, Model model) throws MessagingException {
        marketService.purchaseRequestInsert(purchaseRequest);
        emailService.sendPurchaseRequestMessage(purchaseRequest);
        model.addAttribute("purchaseRequest",purchaseRequest);
        return "market/MarketSaleSuccess";
    }

}
