package com.ourbook.shop.controller.memberController;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.exception.PaymentFailException;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.member.CommonMember;
import com.ourbook.shop.dto.member.NaverMember;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/OurBook")
@Controller
public class MemberFormController {

    private final ViewModelHelper viewModelHelper;

    @Autowired
    public MemberFormController(ViewModelHelper viewModelHelper) {
        this.viewModelHelper = viewModelHelper;
    }

    @GetMapping("")
    public String mainPage(Model model,@AuthenticationPrincipal CustomUserDetail userDetail, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null && session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            model.addAttribute("naverMemberName",naverMember.getName());
            model.addAttribute("naverMemberEmail",naverMember.getEmail());
        }else if (userDetail != null){
            model.addAttribute("commonName",userDetail.getName());
            model.addAttribute("commonEmail",userDetail.getEmail());
        }
        return "main/Main";
    }

    @GetMapping("/1")
    public String memberLoginView(CommonMember commonMember, Model model){
        model.addAttribute("commonMember", commonMember);
        return "member/Login";
    }

    @GetMapping("/2")
    public String memberJoinView(CommonMember commonMember, Model model){
        model.addAttribute("commonMember", commonMember);
        return "member/Join";
    }

    @GetMapping("/myInfo")
    public String memberInfoList(){
        return "member/MyPage";
    }

    @GetMapping("/joinInfo")
    public String memberInfoView(Model model,@AuthenticationPrincipal CustomUserDetail userDetail, HttpServletRequest request){
        HttpSession session = request.getSession(false);
       if(session!=null && session.getAttribute("NAVER")!=null){
           SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
           viewModelHelper.naverMemberInfo(model,naverMember);
       }else {
           log.info("{}",userDetail.getAuthorities());
           viewModelHelper.commonMemberInfo(model,userDetail);
       }
        return "member/JoinInfo";
    }


    @GetMapping("/myInfo/Member")
    public String memberUpdateView(Model model,@AuthenticationPrincipal CustomUserDetail userDetail,CommonMember commonMember){
        viewModelHelper.editMemberInfo(userDetail,commonMember);
        viewModelHelper.translateRole(model,userDetail);
        model.addAttribute("commonMember",commonMember);
        return "member/Edit";
    }


    @GetMapping("/myInfo/Member1")
    public String memberDeleteView(){
        return "member/Delete";
    }

}
