package com.ourbook.shop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    BUYER("ROLE_GUEST", "구매자");


    private final String key;
    private final String value;
}
