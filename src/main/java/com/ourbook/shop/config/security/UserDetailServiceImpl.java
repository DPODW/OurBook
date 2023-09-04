package com.ourbook.shop.config.security;

import com.ourbook.shop.dto.member.CommonMember;
import com.ourbook.shop.mapper.memberMapper.FindInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final FindInfoMapper findInfoMapper;


    public UserDetailServiceImpl(FindInfoMapper findInfoMapper) {
        this.findInfoMapper = findInfoMapper;

    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        CommonMember commonMember = findInfoMapper.searchMember(userId);
        if (commonMember==null){
            throw new UsernameNotFoundException("UserDetailService loadByUsername Exception");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(commonMember.getCommonRole()));


        return CustomUserDetail.builder()
                .id(commonMember.getCommonId())
                .password(commonMember.getCommonPwd())
                .authorities(authorities)
                .email(commonMember.getCommonEmail())
                .name(commonMember.getCommonName())
                .build();
    }
}
