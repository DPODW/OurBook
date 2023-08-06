package com.ourbook.shop.controller;

import com.ourbook.shop.dto.Seller;
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
    public String memberLogin(@ModelAttribute Seller seller, BindingResult bindingResult){
        try{
            log.info("컨트롤러에서 받아오는 값 -> {},{}",seller.getSellerId(),seller.getSellerPwd());
          memberService.login(seller);
        }catch(UsernameNotFoundException ex){
            bindingResult.reject("loginFail");
            return "member/Login";
        }
        return "redirect:/OurBook";
    }


    @PostMapping("/2")
    public String memberJoin(@Validated @ModelAttribute Seller seller, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("seller", seller);
            return "member/Join";
        }
        memberService.save(seller);
        return "redirect:/OurBook";
    }
}





