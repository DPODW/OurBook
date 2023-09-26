package com.ourbook.shop.config.auth;

import com.ourbook.shop.dto.member.CommonMember;
import com.ourbook.shop.dto.member.NaverMember;
import com.ourbook.shop.dto.member.Role;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;


@Getter
public class SessionUser implements Serializable {

    public SessionUser(NaverMember naverMember) {
        this.name = naverMember.getBuyerName();
        this.email = naverMember.getBuyerEmail();
        this.role = naverMember.getBuyerRole();
        this.id = naverMember.getBuyerId();
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