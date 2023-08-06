package com.ourbook.shop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum Role {

    BUYER("ROLE_GUEST", "구매자");

    private final String key;
    private final String value;

    Role(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
