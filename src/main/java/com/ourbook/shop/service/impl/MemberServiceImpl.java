package com.ourbook.shop.service.impl;

import com.ourbook.shop.dto.Seller;
import com.ourbook.shop.mapper.FindInfoMapper;
import com.ourbook.shop.mapper.MemberMapper;
import com.ourbook.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    private final FindInfoMapper findInfoMapper;

    private final PasswordEncoder encoder;

    @Override
    public void save(Seller seller) {
         memberMapper.sellerInsert(Seller.saveBuilder(seller,encoder));
    }

    @Override
    public void login(String id, String pwd) {
          Optional.ofNullable(findInfoMapper.searchMember(id)
                .filter(user -> encoder.matches(pwd, user.getSellerPwd()))
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다.")));
    }
}
