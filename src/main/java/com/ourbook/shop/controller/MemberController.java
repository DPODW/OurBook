package com.ourbook.shop.controller;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.CommonMember;
import com.ourbook.shop.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MemberController {

    private final MemberService memberService;

    private final AuthenticationManager authenticationManager;

    private ViewModelHelper viewModelHelper;


    @Autowired
    public MemberController(MemberService memberService, AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/OurBook/1")
    public String memberLogin(@ModelAttribute CommonMember commonMember, BindingResult bindingResult,HttpSession session){
        Authentication authentication = new UsernamePasswordAuthenticationToken(commonMember.getCommonId(), commonMember.getCommonPwd());
        try{
            Authentication authenticated = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticated);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,securityContext);

            /** TODO: sessionMaker 를 사용하게 되면, role 값 바인딩 되지 않아서 예외 발생함. 고로 role 바인딩 html 구성 요소가 필요함 **/
            return "redirect:/OurBook";
        }catch (Exception ex) {
            bindingResult.reject("loginFail");
            return "member/Login";
        }
    }



    @PostMapping("/OurBook/2")
    public String memberJoin(@Validated @ModelAttribute CommonMember commonMember, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("commonMember", commonMember);
            return "member/Join";
        }
        memberService.save(commonMember);
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


    @PutMapping("/OurBook/4")
    public String memberUpdate(@Validated @ModelAttribute CommonMember commonMember, BindingResult bindingResult, Model model,
                             HttpServletRequest request){
        if(bindingResult.hasErrors()){
            model.addAttribute("commonMember",commonMember);
            model.addAttribute("commonRole",commonMember.getCommonRole());
            return "member/Edit";
        }
        memberService.edit(commonMember);
        memberLogout(request);
        return "redirect:/OurBook";
    }

    @DeleteMapping("/OurBook/5")
    public String memberDelete(HttpServletRequest request,@AuthenticationPrincipal CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            log.info("{}",naverMember.getRole().getValue());
            memberService.delete(naverMember.getEmail(),naverMember.getRole().getValue());
        }else
            memberService.delete(userDetail.getUsername(),userDetail.getAuthorities().toString());
        memberLogout(request);
        return "redirect:/OurBook";

    }


}





