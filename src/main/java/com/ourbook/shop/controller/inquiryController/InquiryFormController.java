package com.ourbook.shop.controller.inquiryController;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.inquiry.InquiryInfo;
import com.ourbook.shop.service.inquiryService.InquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class InquiryFormController {

    private final InquiryService inquiryService;

    public InquiryFormController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }


    @GetMapping("/OurBook/inquiry")
    public String InquiryListView(Model model){
        List<InquiryInfo> inquiryList = inquiryService.findInquiryList();
        model.addAttribute("inquiryList",inquiryList);
        return "inquiry/inquiryList";
    }

    @GetMapping("/OurBook/inquiry/form")
    public String InquiryView(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetail userDetail, Model model, InquiryInfo inquiryInfo){
        HttpSession session = request.getSession(false);
        if(session!=null && session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            model.addAttribute("inquiryWriter",naverMember.getEmail());
        }else {
            model.addAttribute("inquiryWriter",userDetail.getUsername());
        }
        model.addAttribute("inquiryInfo",inquiryInfo);
        return "inquiry/inquiryForm";
    }


}
