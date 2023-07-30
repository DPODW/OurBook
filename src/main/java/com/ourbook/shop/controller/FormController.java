package com.ourbook.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
    public String joinPage(){
        return "member/Join";
    }
}
