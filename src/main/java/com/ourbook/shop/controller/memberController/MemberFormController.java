package com.ourbook.shop.controller.memberController;

import com.ourbook.shop.config.auth.session.NaverMember;
import com.ourbook.shop.config.auth.session.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.member.CommonMember;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String mainPage(@NaverMember SessionUser sessionUser,@AuthenticationPrincipal CustomUserDetail userDetail,Model model){
        if(sessionUser!=null){
            model.addAttribute("naverMemberName",sessionUser.getName());
            model.addAttribute("naverMemberEmail",sessionUser.getEmail());
        }else if (userDetail != null){
            model.addAttribute("commonName",userDetail.getName());
            model.addAttribute("commonEmail",userDetail.getEmail());
        }
        return "main/Main";
    }

    @GetMapping("/login")
    public String memberLoginView(CommonMember commonMember, Model model){
        model.addAttribute("commonMember", commonMember);
        return "member/memberLogin";
    }

    @GetMapping("/join")
    public String memberJoinView(CommonMember commonMember, Model model){
        model.addAttribute("commonMember", commonMember);
        return "member/memberJoin";
    }

    @GetMapping("/joinList")
    public String memberInfoList(){
        return "member/memberPage";
    }


    @GetMapping("/joinInfo")
    public String memberInfoView(@NaverMember SessionUser sessionUser,@AuthenticationPrincipal CustomUserDetail userDetail , Model model){
       if(sessionUser != null){
           viewModelHelper.naverMemberInfo(model,sessionUser);
       }else {
           viewModelHelper.commonMemberInfo(model,userDetail);
       }
        return "member/memberJoinInfo";
    }


    @GetMapping("/joinInfo/member/edit")
    public String memberUpdateView(Model model,@AuthenticationPrincipal CustomUserDetail userDetail,CommonMember commonMember){
        viewModelHelper.editMemberInfo(userDetail,commonMember);
        viewModelHelper.translateRole(model,userDetail);
        model.addAttribute("commonMember",commonMember);
        return "/member/memberUpdate";
    }


    @GetMapping("/joinInfo/member/remove")
    public String memberDeleteView(){
        return "member/memberDelete";
    }

}
