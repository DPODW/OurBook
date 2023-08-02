package com.ourbook.shop.service.impl;

import com.ourbook.shop.mapper.MemberMapper;
import com.ourbook.shop.service.MemberService;
import com.ourbook.shop.vo.Member;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public void save(Member member) {
         memberMapper.insert(member);
    }
}
