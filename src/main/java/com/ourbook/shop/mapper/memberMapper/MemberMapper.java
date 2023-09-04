package com.ourbook.shop.mapper.memberMapper;


import com.ourbook.shop.dto.member.NaverMember;
import com.ourbook.shop.dto.member.CommonMember;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {

     void commonInsert(CommonMember commonMember);

     void naverInsert(NaverMember naverMember);

     void commonUpdate(CommonMember commonMember);

     void commonDelete(String deleteValue,String role);
     
     void naverDelete(String email);




}
