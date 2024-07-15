package com.ourbook.shop.member;

import com.ourbook.shop.check.RoleCheck;
import com.ourbook.shop.config.auth.session.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.member.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleCheckTest {

    RoleCheck roleCheckController = new RoleCheck();

    private static CustomUserDetail createUserDetailMock(String role) {
        CustomUserDetail mockUserDetail = mock(CustomUserDetail.class);
        when(mockUserDetail.getUsername()).thenReturn("testUserDetailName");
        when(mockUserDetail.getEmail()).thenReturn("testUserDetailName@example.com");

        if(role == "SELLER"){
            Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("SELLER"));
            when(mockUserDetail.getAuthorities()).thenAnswer(answer -> authorities);
        }else if(role == "ADMIN"){
            Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
            when(mockUserDetail.getAuthorities()).thenAnswer(answer -> authorities);
        }

        return mockUserDetail;
    }


    private static SessionUser createNaverMock() {
        SessionUser mockSessionUser = mock(SessionUser.class);
        when(mockSessionUser.getName()).thenReturn("testNaverName");
        when(mockSessionUser.getEmail()).thenReturn("testNaverName@example.com");
        return mockSessionUser;
    }


    @Test
    @DisplayName("네이버 회원만 접근 가능한 테스트")
    void checkNaver(){

        /* when */
        ResponseEntity<String> response = roleCheckController.checkNaver(createNaverMock());

        /* then */
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }



    @Test
    @DisplayName("판매자 권한만 접근 가능한 기능 테스트")
    void checkSellerRole(){

        /* when */
        ResponseEntity<String> response = roleCheckController.checkSellerRole(createUserDetailMock("SELLER"));

        /* then */
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DisplayName("본인과 관리자 권한만 접근 가능한 기능 테스트")
    void allowAuthorizedUsersOnly(){
        /* given */
        String writerEmail = "testNaverName@example.com";


        /* when */
        ResponseEntity<String> response = roleCheckController.allowAuthorizedUsersOnly(writerEmail, createNaverMock(), createUserDetailMock("wrong"));


        /* then */
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    @DisplayName("관리자 권한만 접근 가능한 기능 테스트")
    void allowAdminUserOnly(){
        /* when */
        ResponseEntity<String> response = roleCheckController.allowAdminUserOnly(createUserDetailMock("ADMIN"));

        /* then */
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("본인만 접근 가능한 기능 테스트")
    void allowWriterUserOnly(){

        /* given */
        String writerEmail = "testNaverName@example.com";

        /* when */
        ResponseEntity<String> response = roleCheckController.allowWriterUserOnly(writerEmail, createNaverMock(), createUserDetailMock("SELLER"));

        /* then */
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);


    }




}
