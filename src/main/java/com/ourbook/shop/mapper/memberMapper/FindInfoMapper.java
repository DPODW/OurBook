package com.ourbook.shop.mapper.memberMapper;



import com.ourbook.shop.dto.member.CommonMember;
import com.ourbook.shop.dto.member.NaverMember;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FindInfoMapper {

    CommonMember searchMember(String commonId);

    String searchId(String id);

    String searchEmail(String email);

    NaverMember searchMemberToEmail (String email);



}
