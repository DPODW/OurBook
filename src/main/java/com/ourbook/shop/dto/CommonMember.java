package com.ourbook.shop.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;


@Builder
@Setter
@Getter
public class CommonMember {

    /**
 * TODO: 1. 가입 시, 이메일 형식을 확인 할 필요가 있음
 * **/

    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9\uAC00-\uD7AF]*")
    @Length(min=2, max= 20)
    private String commonName;

    @NotBlank
    @Length(min=7, max= 20)
    private String commonId;

    @NotBlank
    @Length(min=10, max= 20)
    private String commonPwd;

    @NotBlank
    @Length(min=5, max= 30)
    private String commonEmail;


    @NotBlank
    private String commonRole;


    public CommonMember(String commonName, String commonId, String commonPwd, String commonEmail, String commonRole) {
        this.commonName = commonName;
        this.commonId = commonId;
        this.commonPwd = commonPwd;
        this.commonEmail = commonEmail;
        this.commonRole = commonRole;
    }

    public static CommonMember saveBuilder(CommonMember commonMember, PasswordEncoder passwordEncoder){
        return CommonMember.builder()
                .commonName(commonMember.getCommonName())
                .commonId(commonMember.getCommonId())
                .commonPwd(passwordEncoder.encode(commonMember.getCommonPwd()))
                .commonEmail(commonMember.getCommonEmail())
                .commonRole(commonMember.getCommonRole())
                .build();
    }


}
