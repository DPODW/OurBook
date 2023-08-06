package com.ourbook.shop.mapper;


import com.ourbook.shop.dto.Buyer;
import com.ourbook.shop.dto.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {

     void sellerInsert(Seller seller);

     void buyerInsert(Buyer buyer);
}
