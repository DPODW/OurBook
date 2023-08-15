package com.ourbook.shop.config.security;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.dto.CommonMember;
import com.ourbook.shop.dto.Role;
import com.ourbook.shop.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionMaker {

    private final MemberService memberService;

    public SessionMaker(MemberService memberService) {
        this.memberService = memberService;
    }

    public void saveSessionUser(CommonMember commonMember, HttpSession session) {
        if(commonMember.getCommonRole().equals(Role.BUYER)) {
            memberService.save(commonMember);
            session.setAttribute("BUYER", new SessionUser(commonMember));
        }else
            memberService.save(commonMember);
        session.setAttribute("SELLER", new SessionUser(commonMember));
    }

    public void loginSessionUser(CommonMember commonMember, HttpSession session) {
        if(commonMember.getCommonRole().equals(Role.BUYER)) {
            session.setAttribute("BUYER", new SessionUser(commonMember));
        }else
            session.setAttribute("SELLER", new SessionUser(commonMember));
    }
}
