package com.ourbook.shop.controller.memberController;

import com.ourbook.shop.config.auth.SessionUser;
import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.member.CommonMember;
import com.ourbook.shop.dto.member.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Collection;

@Slf4j
@Component
public class ViewModelHelper {
    /**
     * DTO.setter 와 Model 에 데이터를 넣어주는 클래스
     * 회원 관리 기능에 종속적 클래스임.
     * **/

    protected void editMemberInfo(CustomUserDetail userDetail, CommonMember commonMember) {
        commonMember.setCommonName(userDetail.getName());
        commonMember.setCommonId( userDetail.getUsername());
        commonMember.setCommonPwd(userDetail.getPassword());
        commonMember.setCommonEmail(userDetail.getEmail());
    }

    protected void translateRole(Model model, CustomUserDetail userDetail) {
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        switch (authorities.iterator().next().toString()){
            case "SELLER":
                model.addAttribute("commonRole", Role.SELLER.getValue());
                break;
            case "BUYER":
                model.addAttribute("commonRole", Role.BUYER.getValue());
                break;
            case "ADMIN":
                model.addAttribute("commonRole", Role.ADMIN.getValue());
                break;
        }
    }


    protected void naverMemberInfo(Model model, SessionUser naverMember) {
        model.addAttribute("naverName",naverMember.getName());
        model.addAttribute("naverEmail",naverMember.getEmail());
        model.addAttribute("naverRole",Role.BUYER.getValue());
        /** 네이버(OAuth2) 회원은 권한 "구매자" 고정 임 **/
    }


    protected void commonMemberInfo(Model model, CustomUserDetail userDetail) {
        model.addAttribute("commonName",userDetail.getName());
        model.addAttribute("commonId", userDetail.getUsername());
        model.addAttribute("commonPwd",userDetail.getPassword());
        model.addAttribute("commonEmail",userDetail.getEmail());
        translateRole(model, userDetail);
    }
}
