package com.ourbook.shop.config.auth;

import com.ourbook.shop.mapper.FindInfoMapper;
import com.ourbook.shop.mapper.MemberMapper;
import com.ourbook.shop.dto.NaverMember;
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
        //DefaultOAuth2UserService 라는 기본 OAuth2.0 사용자 서비스를 생성. 이 서비스를 통해 OAuth2.0으로부터 사용자 정보를 가져올수 있음

        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        //앞에서 생성한 서비스를 사용하여 실제 사용자 정보를 얻어옴 (delegate == DefaultOAuth2UserService)

        OAuthAttributes attributes = OAuthAttributes.of(oAuth2User.getAttributes());
        //얻어온 사용자 정보를 of(어느 OAuth2 요청인지) 에 넣어서 확인하고, attribute 에 넣는다.

        NaverMember naverMember = saveOrFind(attributes);
        //attributes 를 DB 작업을 시킨다. (저장 OR 검색)

        log.info("{}",naverMember);
        httpSession.setAttribute("buyer", new SessionUser(naverMember));
        //모든 작업이 끝나면 인증된 세션 객체인 SessionUser 에 buyer 을 넣고, 인증 세션을 제공한다.

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(naverMember.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    //DB에 동일한 정보가 없으면 저장, 있으면 저장하지 않고 반환한다.
    /* TODO: 동일한 이메일이면 비밀번호를 업데이트 하는 방식으로 구현해도 좋다 */
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
