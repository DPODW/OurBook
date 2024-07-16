package com.ourbook.shop.member;

import com.ourbook.shop.check.RoleCheck;
import com.ourbook.shop.config.auth.session.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@WebMvcTest(RoleCheck.class)
public class RoleCheckTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("네이버 회원 접근 불가 기능 테스트")
    @WithMockUser(username = "테스트용 계정")
    void checkNaver() throws Exception {
        MockHttpSession session = getMockNaverSession();

        mockMvc.perform(post("/checkNaver").session(session).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("판매자 권한만 접근 가능한 기능 테스트")
    @WithMockUser(username = "테스트용 계정")
    void checkSellerRole() throws Exception {

        mockMvc.perform(post("/checkRole")
                .with(SecurityMockMvcRequestPostProcessors.user(createUserDetailMock("SELLER")))
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @DisplayName("본인과 관리자 권한만 접근 가능한 기능 테스트")
    @WithMockUser(username = "테스트용 계정")
    void allowAuthorizedUsersOnly() throws Exception {
        MockHttpSession session = getMockNaverSession();

        mockMvc.perform(post("/checkAuthorizedUser/testNaverName@example.com")
                .with(SecurityMockMvcRequestPostProcessors.user(createUserDetailMock("SELLER")))
                .session(session).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }



    @Test
    @DisplayName("관리자 권한만 접근 가능한 기능 테스트")
    @WithMockUser(username = "테스트용 계정")
    void allowAdminUserOnly() throws Exception {
        mockMvc.perform(post("/checkAuthorizedUser/testNaverName@example.com")
                .with(SecurityMockMvcRequestPostProcessors.user(createUserDetailMock("ADMIN")))
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @DisplayName("본인만 접근 가능한 기능 테스트")
    @WithMockUser(username = "테스트용 계정")
    void allowWriterUserOnly() throws Exception {
        MockHttpSession session = getMockNaverSession();

        mockMvc.perform(post("/checkMe/testNaverName@example.com")
                .with(SecurityMockMvcRequestPostProcessors.user(createUserDetailMock("SELLER")))
                .session(session).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

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

    private static MockHttpSession getMockNaverSession() {
        SessionUser sessionUser = createNaverMock();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("NAVER", sessionUser);
        return session;
    }



}
