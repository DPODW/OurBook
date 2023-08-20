package com.ourbook.shop.controller;

import com.ourbook.shop.config.security.SessionMaker;
import com.ourbook.shop.config.security.UserDetailServiceImpl;
import com.ourbook.shop.dto.CommonMember;
import com.ourbook.shop.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final AuthenticationManager authenticationManager;


    @Autowired
    public MemberController(MemberService memberService, SessionMaker sessionMaker, AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.sessionMaker = sessionMaker;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/OurBook/1")
    public String memberLogin(@ModelAttribute CommonMember commonMember, BindingResult bindingResult){
        Authentication authentication = new UsernamePasswordAuthenticationToken(commonMember.getCommonId(), commonMember.getCommonPwd());
        try{
            Authentication authenticated = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticated);

            //여기도 세션 주는게 좋을듯 인증/세션 별개 . . . 리다이렉트는 sucess handler 따로 구현
            return "main/Main";
        }catch (Exception ex) {
            bindingResult.reject("loginFail");
            return "member/Login";
        }
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

    @PostMapping("/OurBook/3")
    public String memberLogout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
            log.info("세션 정상 삭제");
        }else
            log.info("세션 존재 x");
        return "redirect:/OurBook";
    }


}





