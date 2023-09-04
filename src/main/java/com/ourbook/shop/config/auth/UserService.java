package com.ourbook.shop.config.auth;

import com.ourbook.shop.mapper.memberMapper.FindInfoMapper;
import com.ourbook.shop.mapper.memberMapper.MemberMapper;
import com.ourbook.shop.dto.member.NaverMember;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@Slf4j
public class UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final HttpSession httpSession;

    private final FindInfoMapper findInfoMapper;

    private final MemberMapper memberMapper;

    @Autowired
    public UserService(HttpSession httpSession, FindInfoMapper findInfoMapper, MemberMapper memberMapper) {
        this.httpSession = httpSession;
        this.findInfoMapper = findInfoMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        OAuthAttributes attributes = OAuthAttributes.of(oAuth2User.getAttributes());
        NaverMember naverMember = saveOrFind(attributes);
        httpSession.setAttribute("NAVER", new SessionUser(naverMember));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(naverMember.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    /** TODO: 동일한 이메일이면 다른 정보가 있을시 해당 정보를 업데이트 하는 방식으로 구현해도 좋다. **/
    private NaverMember saveOrFind(OAuthAttributes attributes) {
        NaverMember naverMember;
        if (findInfoMapper.searchMemberToEmail(attributes.getEmail()) == null) {
            naverMember = attributes.toSave();
            memberMapper.naverInsert(naverMember);
        }
        naverMember =findInfoMapper.searchMemberToEmail(attributes.getEmail());
        return naverMember;
    }
}
