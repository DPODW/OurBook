package com.ourbook.shop.service;

import com.ourbook.shop.dto.CommonMember;
import org.springframework.stereotype.Service;


@Service
public interface MemberService {

    void save(CommonMember commonMember);

    void login(CommonMember commonMember);

}
