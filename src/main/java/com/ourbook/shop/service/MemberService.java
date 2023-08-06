package com.ourbook.shop.service;

import com.ourbook.shop.dto.Seller;
import org.springframework.stereotype.Service;


@Service
public interface MemberService {

    void save(Seller seller);

    void login(Seller seller);

}
