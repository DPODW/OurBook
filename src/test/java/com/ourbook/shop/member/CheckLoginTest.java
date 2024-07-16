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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collection;
import java.util.Collections;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(LoginCheck.class)
public class CheckLoginTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("로그인된 사용자 인증 테스트")
    void checkLogin() throws Exception {
        MockHttpSession session = getMockNaverSession();

        mockMvc.perform(post("/checkLogin").with(SecurityMockMvcRequestPostProcessors.user(createUserDetailMock()))
                .session(session)
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



    private static CustomUserDetail createUserDetailMock() {
        CustomUserDetail mockUserDetail = mock(CustomUserDetail.class);
        when(mockUserDetail.getUsername()).thenReturn("testUserDetailName");
        when(mockUserDetail.getEmail()).thenReturn("testUserDetailName@example.com");
        return mockUserDetail;
    }


    private static SessionUser createNaverMock() {
        SessionUser mockSessionUser = mock(SessionUser.class);
        when(mockSessionUser.getName()).thenReturn("testNaverName");
        when(mockSessionUser.getEmail()).thenReturn("testNaverName@example.com");
        return mockSessionUser;
    }

    private static MockHttpSession getMockNaverSession() {
        SessionUser sessionUser = createNaverMock();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("NAVER", sessionUser);
        return session;
    }



}
