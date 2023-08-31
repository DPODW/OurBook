package com.ourbook.shop.controller.memberController;

import com.ourbook.shop.config.security.CustomUserDetail;
import com.ourbook.shop.dto.CommonMember;
import com.ourbook.shop.dto.Role;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class ViewModelHelper {

    protected void editMemberInfo(CustomUserDetail userDetail, CommonMember commonMember) {
        commonMember.setCommonName(userDetail.getName());
        commonMember.setCommonId( userDetail.getUsername());
        commonMember.setCommonPwd(userDetail.getPassword());
        commonMember.setCommonEmail(userDetail.getEmail());
    }

    protected void translateRole(Model model, CustomUserDetail userDetail) {
        if(userDetail.getAuthorities().toString().equals("[SELLER]")){
            model.addAttribute("commonRole", Role.SELLER.getValue());
        }else{
            model.addAttribute("commonRole",Role.BUYER.getValue());
        }
    }


    protected void naverMemberInfo(Model model, DefaultOAuth2User defaultOAuth2User) {
        model.addAttribute("naverName",defaultOAuth2User.getAttributes().get("name"));
        model.addAttribute("naverEmail",defaultOAuth2User.getAttributes().get("email"));
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
