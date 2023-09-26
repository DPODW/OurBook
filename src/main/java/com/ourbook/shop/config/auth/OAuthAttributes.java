package com.ourbook.shop.config.auth;

import com.ourbook.shop.dto.member.Role;
import com.ourbook.shop.dto.member.NaverMember;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Getter
@Builder
public class OAuthAttributes {

    private final Map<String, Object> attributes;

    private final String nameAttributeKey;
    private final String name;
    private final String email;


    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(Map<String, Object> attributes) {
            return ofNaver("id", attributes);
    }


    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response) //사용자 전체 정보
                .nameAttributeKey(userNameAttributeName) //사용자 이름 속성 키
                .build();
    }


    public NaverMember toSave() {
        return NaverMember.builder()
                .buyerName(name)
                .buyerId("NaverMember")
                .buyerPwd("NaverMember")
                .buyerEmail(email)
                .buyerRole(Role.BUYER)
                .build();
    }
}
