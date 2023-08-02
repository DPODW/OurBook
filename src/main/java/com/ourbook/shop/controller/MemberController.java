package com.ourbook.shop.controller;

import com.ourbook.shop.exception.DuplicateCheckApi;
import com.ourbook.shop.service.MemberService;
import com.ourbook.shop.vo.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final DuplicateCheckApi duplicateCheckApi;


    @PostMapping("/2")
    public String memberJoin(@Validated @ModelAttribute Member member, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("member", member);
            return "member/Join";
        }
        memberService.save(member);
        return "main/Main";
    }
}





