package com.ourbook.shop.controller;

import com.ourbook.shop.vo.Member;
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
    public String loginPage(){
        return "member/Login";
    }

    @GetMapping("/OurBook/2")
    public String joinPage(Member member, Model model){
        model.addAttribute("member",member);
        return "member/Join";
    }
}
