package com.ourbook.shop.service.impl;

import com.ourbook.shop.config.security.UserDetailServiceImpl;
import com.ourbook.shop.dto.CommonMember;
import com.ourbook.shop.dto.Role;
import com.ourbook.shop.mapper.FindInfoMapper;
import com.ourbook.shop.mapper.MemberMapper;
import com.ourbook.shop.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        if (commonMember.getCommonRole().equals(Role.BUYER.getValue())) {
            restoreBuyerRole(commonMember);
            memberMapper.commonInsert(CommonMember.saveBuilder(commonMember, encoder));

        } else if (commonMember.getCommonRole().equals(Role.SELLER.getValue())) {
            restoreSellerRole(commonMember);
            memberMapper.commonInsert(CommonMember.saveBuilder(commonMember, encoder));
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
}