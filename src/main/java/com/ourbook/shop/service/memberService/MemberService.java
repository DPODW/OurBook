package com.ourbook.shop.service.memberService;

import com.ourbook.shop.dto.member.CommonMember;
import org.springframework.stereotype.Service;


@Service
public interface MemberService {

    void save(CommonMember commonMember);

    void edit(CommonMember commonMember);

    void delete(String deleteValue,String role);


}
