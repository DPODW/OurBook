package com.ourbook.shop.config.auth;

import com.ourbook.shop.dto.CommonMember;
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
        this.id = naverMember.getId();
    } /** 네이버 회원 전용 세션 생성자 **/


    public SessionUser(CommonMember commonMember) {
        this.name = commonMember.getCommonName();
        this.email = commonMember.getCommonEmail();
        this.commonRole = commonMember.getCommonRole();
    } /** 일반 회원 전용 세션 생성자 **/

    private String name;

    private String id;

    private String email;

    private Role role;

    private String commonRole;
}