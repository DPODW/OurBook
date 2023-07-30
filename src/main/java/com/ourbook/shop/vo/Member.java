package com.ourbook.shop.vo;

import lombok.Data;

/**
 * @Data 는 안정적이지 않습니다. -> 추후 디테일하게 리팩토링
 * */
@Data
public class Member {

    private String name;
    private String id;
    private String pwd;
    private String email;
    private String role;

    public Member(String name, String id, String pwd, String email, String role) {
        this.name = name;
        this.id = id;
        this.pwd = pwd;
        this.email = email;
        this.role = role;
    }
}
