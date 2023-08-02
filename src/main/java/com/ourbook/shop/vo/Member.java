package com.ourbook.shop.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Member {

    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9\uAC00-\uD7AF]*")//한글 영어 숫자 허용
    @Length(min=2, max= 20)
    private String name;

    @NotBlank
    @Length(min=7, max= 20)
    private String id;

    @NotBlank
    @Length(min=10, max= 20)
    private String pwd;

    @NotBlank
    @Length(min=5, max= 30)
    private String email;

    @NotBlank
    private String role;

    public Member(String name, String id, String pwd, String email, String role) {
        this.name = name;
        this.id = id;
        this.pwd = pwd;
        this.email = email;
        this.role = role;
    }
}
