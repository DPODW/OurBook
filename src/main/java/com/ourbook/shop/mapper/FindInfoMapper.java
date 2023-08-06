package com.ourbook.shop.mapper;



import com.ourbook.shop.dto.Buyer;
import com.ourbook.shop.dto.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface FindInfoMapper {

    Optional<Seller> searchMember(String id);

    String searchId(String id);

    String searchEmail(String email);

    Buyer searchMemberToEmail (String email);



}
