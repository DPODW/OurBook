package com.ourbook.shop.controller.memberController;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.member.CommonMember;
import com.ourbook.shop.service.memberService.MemberService;
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
@RequestMapping("/OurBook")
@Controller
public class MemberController {

    private final MemberService memberService;

    private final AuthenticationManager authenticationManager;



    @Autowired
    public MemberController(MemberService memberService, AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/1")
    public String memberLogin(@ModelAttribute CommonMember commonMember, BindingResult bindingResult,HttpSession session){
        Authentication authentication = new UsernamePasswordAuthenticationToken(commonMember.getCommonId(), commonMember.getCommonPwd());
        try{
            Authentication authenticated = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticated);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,securityContext);
            return "redirect:/OurBook";
        }catch (Exception ex) {
            bindingResult.reject("loginFail");
            return "member/Login";
        }
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

    @PostMapping("/3")
    public String memberLogout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }else
            log.info("session is already empty");
        return "redirect:/OurBook";
    }


    @PutMapping("/4")
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


    @DeleteMapping("/5")
    public String memberDelete(HttpServletRequest request,@AuthenticationPrincipal CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("NAVER")!=null){
            SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
            memberService.delete(naverMember.getEmail(),naverMember.getRole().getValue());
        }else
            memberService.delete(userDetail.getUsername(),userDetail.getAuthorities().iterator().next().toString());
            //toString 하여서 넘기는 이유는, naverMember 와 UserDetail 의 role 타입이 다른데, service 를 공유해서 사용해야 함.
            //그래서 두 객체를 모두 문자로 변환 후, String 으로 넘겨버리는 것. (naver == Role / userDetail == Collection<? extends GrantedAuthority>)
        memberLogout(request);
        return "redirect:/OurBook";
    }
}





