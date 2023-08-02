package com.ourbook.shop.mapper;


import com.ourbook.shop.vo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {

     void insert(Member member);
}
