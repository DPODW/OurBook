package com.ourbook.shop.controller;

import com.ourbook.shop.dto.CommonMember;
import com.ourbook.shop.service.MemberService;
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


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/1")
    public String memberLogin(@ModelAttribute CommonMember commonMember, BindingResult bindingResult){
        try{
          memberService.login(commonMember);
        }catch(UsernameNotFoundException ex){
            bindingResult.reject("loginFail");
            return "member/Login";
        }
        return "redirect:/OurBook";
    }


    @PostMapping("/2")
    public String memberJoin(@Validated @ModelAttribute CommonMember commonMember, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("commonMember", commonMember);
            return "member/Join";
        }
        memberService.save(commonMember);
        return "redirect:/OurBook";
    }
}





