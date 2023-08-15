package com.ourbook.shop.controller;

import com.ourbook.shop.config.security.SessionMaker;
import com.ourbook.shop.dto.CommonMember;
import com.ourbook.shop.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {

    private final MemberService memberService;

    private final SessionMaker sessionMaker;


    @Autowired
    public MemberController(MemberService memberService, SessionMaker sessionMaker) {
        this.memberService = memberService;
        this.sessionMaker = sessionMaker;
    }

    @PostMapping("/OurBook/1")
    public String memberLogin(@ModelAttribute CommonMember commonMember, BindingResult bindingResult,HttpSession session){
        try{
            CommonMember login = memberService.login(commonMember);
            sessionMaker.loginSessionUser(login,session);
        }catch(UsernameNotFoundException ex){
            bindingResult.reject("loginFail");
            return "member/Login";
        }
        return "redirect:/OurBook";
    }


    @PostMapping("/OurBook/2")
    public String memberJoin(@Validated @ModelAttribute CommonMember commonMember, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("commonMember", commonMember);
            return "member/Join";
        }
        sessionMaker.saveSessionUser(commonMember,session);
        return "redirect:/OurBook";
    }
}





