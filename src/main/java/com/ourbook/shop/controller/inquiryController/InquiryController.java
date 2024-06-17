package com.ourbook.shop.controller.inquiryController;


import com.ourbook.shop.config.auth.session.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.inquiry.InquiryAnswerInfo;
import com.ourbook.shop.dto.inquiry.InquiryInfo;
import com.ourbook.shop.service.inquiryService.InquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @PostMapping("/OurBook/inquiry/form")
    public String InquirySave(
            HttpServletRequest request,@AuthenticationPrincipal CustomUserDetail userDetail,
            @Validated @ModelAttribute InquiryInfo inquiryInfo, BindingResult bindingResult, @RequestParam("inquiryWriter")String inquiryWriter,
                              Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("inquiryWriter",inquiryWriter);
           /* 최초 조회시 GetMapping 에서 제공해주는 inquiryWriter 파라미터는,bindingResult 로 인한 view 재요청에서 값이 유지되지 않기 때문에,
           * @RequestParam 을 통해 값을 미리 받아오고, 다시 뿌려줘야한다.*/
            model.addAttribute("inquiryInfo", inquiryInfo);
            return "inquiry/inquiryForm";
        }

        HttpSession session = request.getSession(false);
        if(session!=null && session.getAttribute("NAVER")!= null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            inquiryInfo.setInquiryEmail(naverMember.getEmail());
        }else{
            inquiryInfo.setInquiryEmail(userDetail.getEmail());
        }
        inquiryService.inquirySave(inquiryInfo);
        return "redirect:/OurBook/inquiry";
    }

    @PutMapping("/OurBook/inquiry/edit")
    public String InquiryEdit(@Validated @ModelAttribute InquiryInfo inquiryInfo,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("inquiryInfo",inquiryInfo);
            return "inquiry/inquiryEdit";
        }
        inquiryService.inquiryEdit(inquiryInfo);
        return "redirect:/OurBook/inquiry";
    }

    @DeleteMapping("/OurBook/inquiry/delete/{inquiryNumber}")
    public String InquiryDelete(@PathVariable int inquiryNumber){
        inquiryService.inquiryDelete(inquiryNumber);
        return "redirect:/OurBook/inquiry";
    }



    @PostMapping("/OurBook/inquiry/admin/answer")
    public String InquiryAnswerSave(@ModelAttribute InquiryAnswerInfo inquiryAnswerInfo){
        inquiryService.inquiryAnswerSave(inquiryAnswerInfo);
        return "redirect:/OurBook/inquiry";
    }

    @DeleteMapping("/OurBook/inquiry/admin/answer/{inquiryNumber}")
    public String InquiryAnswerDelete(@PathVariable int inquiryNumber){
        inquiryService.inquiryAnswerDelete(inquiryNumber);
        return "redirect:/OurBook/inquiry";
    }


    @ResponseBody
    @PostMapping("/checkAlreadyAnswer/{inquiryNumber}")
    public ResponseEntity<String> checkAlreadyAnswer(@PathVariable int inquiryNumber){
        InquiryAnswerInfo inquiryAnswer = inquiryService.findInquiryAnswer(inquiryNumber);
        if(inquiryAnswer!=null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 답변이 등록된 문의 입니다. 수정 불가");
        }else
            return ResponseEntity.ok().body("수정 가능");
    }

}
