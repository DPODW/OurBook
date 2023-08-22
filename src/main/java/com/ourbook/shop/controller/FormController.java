package com.ourbook.shop.controller;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.dto.CommonMember;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class FormController {



    @GetMapping("/OurBook")
    public String mainPage(){
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

    @GetMapping("/OurBook/myInfo")
    public String memberPage(){
        return "member/MyPage";
    }

    @GetMapping("/OurBook/joinInfo")
    public String memberInfo(){
        return "member/JoinInfo";
    }

}
