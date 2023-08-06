package com.ourbook.shop.service.impl;

import com.ourbook.shop.dto.Seller;
import com.ourbook.shop.mapper.FindInfoMapper;
import com.ourbook.shop.mapper.MemberMapper;
import com.ourbook.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NameNotFoundException;
import java.util.Optional;


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
    public void save(Seller seller) {
         memberMapper.sellerInsert(Seller.saveBuilder(seller,encoder));
    }

    @Override
    public void login(Seller seller) {
        Seller seller1 = findInfoMapper.searchMember(seller.getSellerId());
        if(seller1!=null){
            encoder.matches(seller.getSellerPwd(),seller1.getSellerPwd());
        }else
            throw new UsernameNotFoundException("존재 x 회원");
    }

}
