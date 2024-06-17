package com.ourbook.shop.config;

import com.ourbook.shop.config.auth.session.NaverMemberArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final NaverMemberArgumentResolver naverMemberArgumentResolver;

    public WebConfig(NaverMemberArgumentResolver naverMemberArgumentResolver) {
        this.naverMemberArgumentResolver = naverMemberArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(naverMemberArgumentResolver);
    }
}
