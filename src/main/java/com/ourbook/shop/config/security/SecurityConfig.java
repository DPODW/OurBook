package com.ourbook.shop.config.security;

import com.ourbook.shop.config.auth.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j
@Configuration
@RequiredArgsConstructor
@PropertySource(value = "classpath:application.properties")
public class SecurityConfig {
    String[] publicForm = {"/OurBook", "/OurBook/1", "/OurBook/2","/OurBook/3", "/oauth2/authorization/naver", "/OurBook/book",

            "/OurBook/book/info/{bookId}", "/checkLogin", "/iamports/accessToken","/OurBook/market", "/OurBook/market/sale/info/{number}", "/checkRole","/OurBook/inquiry",

            "/checkAuthorizedUser/{writer}","/checkAlreadyAnswer/{inquiryNumber}","/checkMe/{writer}", "/OurBook/book/search", "/OurBook/findNearestLibrary", "/OurBook/findNearestLibrary/myLocation",

            "/OurBook/test",

            "/checkId", "/checkEmail","/css/**", "/js/**", "/img/**"};

    private final UserDetailServiceImpl userDetailsService;

    private final UserService userService;

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable())
                .authorizeHttpRequests(request -> request.requestMatchers(publicForm).permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(authenticationEntryPoint))
                .oauth2Login(httpSecurityOAuth2LoginConfigurer -> httpSecurityOAuth2LoginConfigurer
                        .successHandler(successHandler())
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(userService)));
        return http.build();
    }

    private AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("/OurBook");
        };
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService, passwordEncoder());
    }
   

}
