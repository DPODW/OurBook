package com.ourbook.shop.check;


import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SellerRoleCheck {

    @PostMapping("/roleCheck")
    public ResponseEntity<String> checkSellerRole(@AuthenticationPrincipal CustomUserDetail userDetail ){
        if(userDetail!=null && userDetail.getAuthorities().toString().equals("[SELLER]")){
            return ResponseEntity.ok().body("판매자 권한 있음");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매자 권한 없음");
        }
    }

}
