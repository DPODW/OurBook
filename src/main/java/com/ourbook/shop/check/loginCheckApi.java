package com.ourbook.shop.check;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class loginCheckApi {

    @PostMapping("/checkLogin")
    public ResponseEntity<String> checkLogin(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetail userDetail){
      HttpSession session = request.getSession(false);
      SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
      if(naverMember==null && userDetail==null){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 하지 않은 이용자");
      }else{
          return ResponseEntity.ok().body("로그인 된 이용자");
      }
    }
}
