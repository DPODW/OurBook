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
      if(naverMember==null && userDetail==null){ //빈 껍데기 userDetail 이 생성되기 때문에, email 을 가져올수 있느냐 라는 조건으로 판별
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 하지 않은 이용자");
      }else{
          return ResponseEntity.ok().body("로그인 된 이용자");
      }
    }
}
