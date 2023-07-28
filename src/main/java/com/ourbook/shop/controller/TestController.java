package com.ourbook.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class TestController {

    @GetMapping("/main")
    public String testPage1(){
        return "main/Main";
    }

    @GetMapping("/login")
    public String testPage2(){
        return "member/Login";
    }

    @GetMapping("/join")
    public String testPage3(){
        return "member/Join";
    }
}
