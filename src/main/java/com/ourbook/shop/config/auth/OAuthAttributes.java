package com.ourbook.shop.config.auth;

import com.ourbook.shop.dto.Role;
import com.ourbook.shop.dto.NaverMember;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Slf4j
@Getter
@Builder
public class OAuthAttributes {

    private final Map<String, Object> attributes;

    private final String nameAttributeKey;
    private final String name;
    private final String email;

    @Autowired
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    //OAuth2 로그인 방법이 네이버 , 구글 . . . 등 여러개 일 때, 이곳에서 어느 로그인인지 판별하는 로직을 짤 수 있다.
    //현재는 네이버 하나만 있기 때문에, 네이버 만을 반환한다.
    public static OAuthAttributes of(Map<String, Object> attributes) {
            return ofNaver("id", attributes);
    }


    //네이버 로그인일 시, 실행되는 코드
    //로그인 응답 객체(MAP) 에서 필요한 정보를 GET 해 온다.
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response) //사용자 전체 정보
                .nameAttributeKey(userNameAttributeName) //사용자 이름 속성 키
                .build();
    }


    //로그인 한 정보가 DB에 없을때만 실행 (자동 회원가입)
    public NaverMember toSave() {
        return NaverMember.builder()
                .name(name)
                .id("네이버 로그인 회원")
                .pwd("네이버 로그인 회원")
                .email(email)
                .role(Role.BUYER)
                .build();
    }
}
