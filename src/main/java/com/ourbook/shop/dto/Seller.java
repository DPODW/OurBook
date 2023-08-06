package com.ourbook.shop.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;



@Builder
@Setter
@Getter
public class Seller {



    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9\uAC00-\uD7AF]*")//한글 영어 숫자 허용
    @Length(min=2, max= 20)
    private String sellerName;

    @NotBlank
    @Length(min=7, max= 20)
    private String sellerId;

    @NotBlank
    @Length(min=10, max= 20)
    private String sellerPwd;

    @NotBlank
    @Length(min=5, max= 30)
    private String sellerEmail;


    @NotBlank
    private String sellerRole;

    public Seller(String sellerName, String sellerId, String sellerPwd, String sellerEmail, String sellerRole) {
        this.sellerName = sellerName;
        this.sellerId = sellerId;
        this.sellerPwd = sellerPwd;
        this.sellerEmail = sellerEmail;
        this.sellerRole = sellerRole;
    }

    public static Seller saveBuilder(Seller seller, PasswordEncoder passwordEncoder){
        return Seller.builder()
                .sellerName(seller.getSellerName())
                .sellerId(seller.getSellerId())
                .sellerPwd(passwordEncoder.encode(seller.getSellerPwd()))
                .sellerEmail(seller.getSellerEmail())
                .sellerRole(seller.getSellerRole())
                .build();
    }


}
