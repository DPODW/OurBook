package com.ourbook.shop.dto.member;

import lombok.Getter;

@Getter
public enum Role {

    BUYER("ROLE_BUYER", "구매자"),
    SELLER("ROLE_SELLER","판매자"),
    ADMIN("ROLE_ADMIN", "관리자");



    private final String key;
    private final String value;

    Role(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
