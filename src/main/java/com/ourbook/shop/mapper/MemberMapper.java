package com.ourbook.shop.mapper;


import com.ourbook.shop.dto.NaverMember;
import com.ourbook.shop.dto.CommonMember;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {

     void sellerInsert(CommonMember commonMember);

     void buyerInsert(CommonMember commonMember);

     void naverInsert(NaverMember naverMember);
}
