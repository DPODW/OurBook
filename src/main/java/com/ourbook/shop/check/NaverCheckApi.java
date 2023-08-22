package com.ourbook.shop.check;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class NaverCheckApi {

    @PostMapping("/checkNaver")
    public ResponseEntity<String> checkNaver(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Object naverMember = session.getAttribute("NAVER");
        if(naverMember!=null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("네이버 회원은 회원 수정 불가");
        }else
            return ResponseEntity.ok().body("일반 회원 접근 가능");
    }
}
