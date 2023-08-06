package com.ourbook.shop.config.auth;

import com.ourbook.shop.dto.Buyer;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    public SessionUser(Buyer buyer) {
        this.name = buyer.getName();
        this.email = buyer.getEmail();
    }

    private String name;

    private String email;


}