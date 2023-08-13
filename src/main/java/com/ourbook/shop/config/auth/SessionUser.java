package com.ourbook.shop.config.auth;

import com.ourbook.shop.dto.NaverMember;
import com.ourbook.shop.dto.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    public SessionUser(NaverMember naverMember) {
        this.name = naverMember.getName();
        this.email = naverMember.getEmail();
        this.role = naverMember.getRole();

    }

    private String name;

    private String email;

    private Role role;


}