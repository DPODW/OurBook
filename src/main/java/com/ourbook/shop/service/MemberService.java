package com.ourbook.shop.service;

import com.ourbook.shop.dto.CommonMember;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public interface MemberService {

    void save(CommonMember commonMember);

    void edit(CommonMember commonMember);


}
