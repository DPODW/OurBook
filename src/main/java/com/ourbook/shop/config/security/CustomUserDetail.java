package com.ourbook.shop.config.security;

import lombok.Builder;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;


@ToString
public class CustomUserDetail implements UserDetails,Serializable {

    private String id;
    private String password;

    private Collection<GrantedAuthority> authorities;

    private String email;

    private String name;



    @Builder
    public CustomUserDetail(String id, String password, Collection<GrantedAuthority> authorities,String email,String name) {
        this.id = id;
        this.password = password;
        this.authorities = authorities;
        this.email = email;
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
