package com.ourbook.shop.member;


import com.ourbook.shop.check.LoginCheck;
import com.ourbook.shop.config.auth.session.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.member.NaverMember;
import com.ourbook.shop.dto.member.Role;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


public class CheckLoginTest {


    @Test
    @DisplayName("로그인된 사용자 인증 테스트")
    void checkLogin(){

        /* given */
        LoginCheck loginCheckController = new LoginCheck();

        SessionUser mockSessionUser = mock(SessionUser.class);
        when(mockSessionUser.getName()).thenReturn("testName");
        when(mockSessionUser.getEmail()).thenReturn("test@example.com");

        CustomUserDetail mockUserDetail = mock(CustomUserDetail.class);
        when(mockUserDetail.getUsername()).thenReturn("testUserId");
        when(mockUserDetail.getEmail()).thenReturn("test@example.com");


        /** when : sessionUser 가 null 이 아니고 userDetail 이 null 인 경우 **/
        ResponseEntity<String> response = loginCheckController.checkLogin(mockSessionUser, null);

        /* then */
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("인증된 사용자", response.getBody());


        /** when : sessionUser 가 null 이고 userDetail 이 null 이 아닌 경우 **/
        response = loginCheckController.checkLogin(null, mockUserDetail);

        /* then */
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("인증된 사용자", response.getBody());


        /** when : sessionUser 와 userDetail 모두 null 인 경우 **/
        response = loginCheckController.checkLogin(null, null);

        /* then */
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("인증되지 않은 사용자", response.getBody());
    }


}
