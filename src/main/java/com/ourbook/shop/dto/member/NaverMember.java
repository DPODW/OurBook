package com.ourbook.shop.dto.member;

import lombok.*;

@ToString

@Getter
public class NaverMember {

    private String buyerName;

    private String buyerEmail;

    private Role buyerRole;

    private String buyerPwd;

    private String buyerId;

    @Builder
    public NaverMember(String buyerName, String buyerId, String buyerPwd, String buyerEmail, Role buyerRole) {
        this.buyerName = buyerName;
        this.buyerId = buyerId;
        this.buyerPwd = buyerPwd;
        this.buyerEmail = buyerEmail;
        this.buyerRole = buyerRole;
    }

    public NaverMember(String buyerName, String buyerEmail, Role buyerRole, String buyerPwd, String buyerId) {
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerRole = buyerRole;
        this.buyerPwd = buyerPwd;
        this.buyerId = buyerId;
    }

    public String getRoleKey() {
        return this.buyerRole.getKey();
    }

}
