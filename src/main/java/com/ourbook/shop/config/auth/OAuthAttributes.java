package com.ourbook.shop.config.auth;

import com.ourbook.shop.dto.Role;
import com.ourbook.shop.dto.Buyer;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class OAuthAttributes {

    private final Map<String, Object> attributes;

    private final String nameAttributeKey;
    private final String name;
    private final String email;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }else
            return ofKakao("id", attributes);


    }
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    public Buyer toEntity() {
        return Buyer.builder()
                .name(name)
                .email(email)
                .role(Role.BUYER)
                .build();
    }
}
