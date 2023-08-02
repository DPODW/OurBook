package com.ourbook.shop.service;

import com.ourbook.shop.vo.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    void save(Member member);

}
