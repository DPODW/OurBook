package com.ourbook.shop.service.impl;

import com.ourbook.shop.dto.CommonMember;
import com.ourbook.shop.dto.Role;
import com.ourbook.shop.mapper.FindInfoMapper;
import com.ourbook.shop.mapper.MemberMapper;
import com.ourbook.shop.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    private final FindInfoMapper findInfoMapper;

    private final PasswordEncoder encoder;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, FindInfoMapper findInfoMapper, PasswordEncoder encoder) {
        this.memberMapper = memberMapper;
        this.findInfoMapper = findInfoMapper;
        this.encoder = encoder;
    }

    @Override
    public void save(CommonMember commonMember) {
        if(commonMember.getCommonRole().equals("구매자")){
            commonMember.setCommonRole(String.valueOf(Role.BUYER));
            memberMapper.buyerInsert(CommonMember.saveBuilder(commonMember,encoder));

        }else if(commonMember.getCommonRole().equals("판매자")){
            commonMember.setCommonRole(String.valueOf(Role.SELLER));
            memberMapper.sellerInsert(CommonMember.saveBuilder(commonMember,encoder));
        }
    }

    @Override
    public void login(CommonMember loginValue) {
        CommonMember loginResult = findInfoMapper.searchMember(loginValue.getCommonId());
        if(loginResult !=null){
            encoder.matches(loginValue.getCommonPwd(), loginResult.getCommonPwd());
        }else
            throw new UsernameNotFoundException("존재 x 회원");
    }

}
