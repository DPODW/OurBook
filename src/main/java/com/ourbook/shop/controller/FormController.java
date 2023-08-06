package com.ourbook.shop.controller;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.dto.Seller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class FormController {

    private HttpSession httpSession;

    @GetMapping("/OurBook")
    public String mainPage(HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("buyer");
            log.info("인증된 세션 -> {},{}",sessionUser.getName(),sessionUser.getEmail());
            return "main/Main";
        } else
            log.info("세션 X ");
        return "main/Main";
    }

    @GetMapping("/OurBook/1")
    public String loginPage(Seller seller, Model model){
        model.addAttribute("seller", seller);
        return "member/Login";
    }

    @GetMapping("/OurBook/2")
    public String joinPage(Seller seller, Model model){
        model.addAttribute("seller", seller);
        return "member/Join";
    }

}
