package com.ourbook.shop.controller.memberController;

import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.member.CommonMember;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MemberFormController {

    private final ViewModelHelper viewModelHelper;

    @Autowired
    public MemberFormController(ViewModelHelper viewModelHelper) {
        this.viewModelHelper = viewModelHelper;
    }

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
           viewModelHelper.naverMemberInfo(model,defaultOAuth2User);
       }else {

           viewModelHelper.commonMemberInfo(model,userDetail);
       }
        return "member/JoinInfo";
    }


    @GetMapping("/OurBook/myInfo/Member")
    public String memberUpdate(Model model,@AuthenticationPrincipal CustomUserDetail userDetail,CommonMember commonMember){
        viewModelHelper.editMemberInfo(userDetail,commonMember);
        viewModelHelper.translateRole(model,userDetail);
        model.addAttribute("commonMember",commonMember);
        return "member/Edit";
    }

    @GetMapping("/OurBook/myInfo/Member1")
    public String memberDelete(){
        return "member/Delete";
    }

}
