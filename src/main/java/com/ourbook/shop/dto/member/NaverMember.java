package com.ourbook.shop.dto.member;

import lombok.*;

@NoArgsConstructor
@Getter
public class NaverMember {

    private String name;

    private String email;

    private Role role;

    private String pwd;

    private String id;

    @Builder
    public NaverMember(String name, String id, String pwd, String email, Role role) {
        this.name = name;
        this.id = id;
        this.pwd = pwd;
        this.email = email;
        this.role = role;
    }


    public String getRoleKey() {
        return this.role.getKey();
    }

}
