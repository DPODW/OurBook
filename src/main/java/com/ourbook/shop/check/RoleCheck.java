package com.ourbook.shop.check;


import com.ourbook.shop.config.auth.session.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RoleCheck {


    @PostMapping("/checkNaver")
    public ResponseEntity<String> checkNaver(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Object naverMember = session.getAttribute("NAVER");
        if(naverMember!=null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("네이버 회원은 회원 수정 불가");
        }else
            return ResponseEntity.ok().body("일반 회원 접근 가능");
    }

    @PostMapping("/checkRole")
    public ResponseEntity<String> checkSellerRole(@AuthenticationPrincipal CustomUserDetail userDetail ){
        if(userDetail!=null && userDetail.getAuthorities().iterator().next().toString().equals("SELLER")){
            return ResponseEntity.ok().body("판매자 권한 있음");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매자 권한 없음");
        }
    }

    @PostMapping("/checkAuthorizedUser/{writer}")
    public ResponseEntity<String> allowAuthorizedUsersOnly(@PathVariable String writer, HttpServletRequest request,
                                                         @AuthenticationPrincipal CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
        if(naverMember==null && userDetail==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 되지 않은 사용자");
        }
        if(isAccessPermit(writer, userDetail, naverMember)){
            return ResponseEntity.ok().body("인증된 사용자");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("권한 없는 사용자");
        }
    }


    @PostMapping("/checkAdminUser")
    public ResponseEntity<String> allowAdminUserOnly(HttpServletRequest request,@AuthenticationPrincipal CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
        if(naverMember==null && userDetail.getAuthorities().iterator().next().toString().equals("ADMIN")){
            return ResponseEntity.ok().body("관리자 접근 가능");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("권한 없는 사용자");
        }
    }

    @PostMapping("/checkMe/{writerEmail}")
    public ResponseEntity<String> allowWriterUserOnly(@PathVariable String writerEmail,HttpServletRequest request,@AuthenticationPrincipal CustomUserDetail userDetail){
        HttpSession session = request.getSession(false);
        SessionUser naverMember = (SessionUser) session.getAttribute("NAVER");
        if(naverMember != null && naverMember.getEmail().equals(writerEmail) ||
           userDetail != null && userDetail.getEmail().equals(writerEmail)){
            return ResponseEntity.ok().body("본인 접근 가능");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("권한 없는 사용자");
        }
    }


    private static boolean isAccessPermit(String writer, CustomUserDetail userDetail, SessionUser naverMember) {
        return  (naverMember != null && naverMember.getEmail() != null && naverMember.getEmail().equals(writer)) ||
                (userDetail != null && userDetail.getEmail() != null && userDetail.getEmail().equals(writer)) ||
                (userDetail != null && userDetail.getAuthorities() != null && !userDetail.getAuthorities().isEmpty() &&
                 userDetail.getAuthorities().iterator().next().toString().equals("ADMIN"));
        /** NULL 체크를 하지 않아도 정상 작동은 하나,확실한 조건 검사를 위해 NULL 체크 로직을 추가함 **/
    }
}

