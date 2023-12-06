package com.ourbook.shop.service.memberService.impl;

import com.ourbook.shop.dto.member.CommonMember;
import com.ourbook.shop.dto.member.Role;
import com.ourbook.shop.mapper.memberMapper.MemberMapper;
import com.ourbook.shop.service.memberService.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    private final PasswordEncoder encoder;


    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, PasswordEncoder encoder) {
        this.memberMapper = memberMapper;
        this.encoder = encoder;

    }

    @Override
    public void save(CommonMember commonMember) {
        switch (commonMember.getCommonRole()){
            case "구매자": restoreBuyerRole(commonMember);
                memberMapper.commonInsert(CommonMember.saveBuilder(commonMember, encoder));
                break;
            case "판매자": restoreSellerRole(commonMember);
                memberMapper.commonInsert(CommonMember.saveBuilder(commonMember, encoder));
                break;
            case "관리자": restoreAdminRole(commonMember);
                memberMapper.commonInsert(CommonMember.saveBuilder(commonMember, encoder));
                break;
        }
    }

    @Override
    public void edit(CommonMember commonMember) {
        if (commonMember.getCommonRole().equals(Role.BUYER.getValue())) {
            restoreBuyerRole(commonMember);
            memberMapper.commonUpdate(CommonMember.saveBuilder(commonMember, encoder));

        } else if (commonMember.getCommonRole().equals(Role.SELLER.getValue())) {
            restoreSellerRole(commonMember);
            memberMapper.commonUpdate(CommonMember.saveBuilder(commonMember, encoder));
        }
    }

    @Override
    public void delete(String deleteValue, String role) {
            if (deleteValue.contains("@")) {
                memberMapper.naverDelete(deleteValue);
            } else
                memberMapper.commonDelete(deleteValue,role);
        }


    private static void restoreSellerRole(CommonMember commonMember) {
        commonMember.setCommonRole(String.valueOf(Role.SELLER));
    }

    private static void restoreBuyerRole(CommonMember commonMember) {
        commonMember.setCommonRole(String.valueOf(Role.BUYER));
    }

    private static void restoreAdminRole(CommonMember commonMember) {
        commonMember.setCommonRole(String.valueOf(Role.ADMIN));
    }
}