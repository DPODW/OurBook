package com.ourbook.shop.controller;

import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.CommonMember;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
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
    public String memberInfo(Model model,@AuthenticationPrincipal CustomUserDetail userDetail,
                             @AuthenticationPrincipal DefaultOAuth2User defaultOAuth2User,HttpServletRequest request){
        HttpSession session = request.getSession(false);
       if(session.getAttribute("NAVER")!=null){
           naverMemberInfo(model, defaultOAuth2User);
       }else {
           commonMemberInfo(model, userDetail);
       }
        return "member/JoinInfo";
    }


    @GetMapping("/OurBook/myInfo/Member")
    public String memberUpdate(){
        return "member/Edit";
    }


    private static void naverMemberInfo(Model model, DefaultOAuth2User defaultOAuth2User) {
        model.addAttribute("naverName",defaultOAuth2User.getAttributes().get("name"));
        model.addAttribute("naverEmail",defaultOAuth2User.getAttributes().get("email"));
        model.addAttribute("naverRole",defaultOAuth2User.getAuthorities().toString());
    }

    private static void commonMemberInfo(Model model, CustomUserDetail userDetail) {
        model.addAttribute("commonName",userDetail.getName());
        model.addAttribute("commonId", userDetail.getUsername());
        model.addAttribute("commonPwd",userDetail.getPassword());
        model.addAttribute("commonEmail",userDetail.getEmail());
        model.addAttribute("commonRole",userDetail.getAuthorities().toString());
    }



}
