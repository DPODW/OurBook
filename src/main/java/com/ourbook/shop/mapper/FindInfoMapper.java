package com.ourbook.shop.mapper;



import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FindInfoMapper {

    String searchId(String id);

    String searchEmail(String id);

}
