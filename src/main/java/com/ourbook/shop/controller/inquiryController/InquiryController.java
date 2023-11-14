package com.ourbook.shop.controller.inquiryController;


import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.inquiry.InquiryAnswerInfo;
import com.ourbook.shop.dto.inquiry.InquiryInfo;
import com.ourbook.shop.service.inquiryService.InquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @PostMapping("/OurBook/inquiry/form")
    public String InquirySave(@Validated @ModelAttribute InquiryInfo inquiryInfo, BindingResult bindingResult, @RequestParam("inquiryWriter")String inquiryWriter,
                              Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("inquiryWriter",inquiryWriter);
           /* 최초 조회시 GetMapping 에서 제공해주는 inquiryWriter 파라미터는,bindingResult 로 인한 view 재요청에서 값이 유지되지 않기 때문에,
           * @RequestParam 을 통해 값을 미리 받아오고, 다시 뿌려줘야한다.*/
            model.addAttribute("inquiryInfo", inquiryInfo);
            return "inquiry/inquiryForm";
        }
        inquiryService.inquirySave(inquiryInfo);
        return "redirect:/OurBook/inquiry";
    }

    @PostMapping("/OurBook/inquiry/admin/answer")
    public String InquiryAnswer(@ModelAttribute InquiryAnswerInfo inquiryAnswerInfo){
        inquiryService.inquiryAnswerSave(inquiryAnswerInfo);
        return "redirect:/OurBook/inquiry";
    }



}
