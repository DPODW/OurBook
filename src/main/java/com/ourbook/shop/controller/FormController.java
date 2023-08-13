package com.ourbook.shop.controller;

import com.ourbook.shop.dto.CommonMember;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class FormController {



    @GetMapping("/OurBook")
    public String mainPage(HttpServletRequest request){
//        HttpSession session =request.getSession(false);
//        SessionUser sessionUser = (SessionUser) session.getAttribute("buyer");
//        log.info("{},{},{}",sessionUser.getName(),sessionUser.getEmail(),sessionUser.getRole());
        return "main/Main";
    }

    @GetMapping("/OurBook/1")
    public String loginPage(CommonMember commonMember, Model model){
        model.addAttribute("commonMember", commonMember);
        return "member/Login";
    }

    @GetMapping("/OurBook/2")
    public String joinPage(CommonMember commonMember, Model model){
        model.addAttribute("commonMember", commonMember);
        return "member/Join";
    }

}
