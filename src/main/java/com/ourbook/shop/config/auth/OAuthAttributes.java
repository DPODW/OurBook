package com.ourbook.shop.config.auth;

import com.ourbook.shop.dto.Role;
import com.ourbook.shop.dto.Buyer;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    public static OAuthAttributes of(Map<String, Object> attributes) {
            return ofNaver("id", attributes);
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


    public Buyer toEntity() {
        return Buyer.builder()
                .name(name)
                .email(email)
                .role(Role.BUYER)
                .build();
    }
}
