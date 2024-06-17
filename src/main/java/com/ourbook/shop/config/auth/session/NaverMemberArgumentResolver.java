package com.ourbook.shop.config.auth.session;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
public class NaverMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    public NaverMemberArgumentResolver(HttpSession httpSession) {
        this.httpSession = httpSession;
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isNaverAnnotation = parameter.getParameterAnnotation(NaverMember.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isNaverAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("NAVER");
    }
}
