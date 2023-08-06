package com.ourbook.shop.dto;

import lombok.*;

@NoArgsConstructor
@Getter
public class Buyer {

    private String name;

    private String email;

    private Role role;

    @Builder
    public Buyer(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }


    public String getRoleKey() {
        return this.role.getKey();
    }

}
