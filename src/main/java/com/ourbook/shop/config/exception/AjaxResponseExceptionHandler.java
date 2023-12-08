package com.ourbook.shop.config.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class AjaxResponseExceptionHandler {

    /**
     * 결제 도중, 서버에서 오류가 발생해도 서버 측에서 예외 처리가 되어서 Ajax 까지 에러가 도달하지 않는 경우가 있다.
     * Ajax 까지 에러가 도달해야, Ajax error 부분을 실행할 수 있기 때문에 에러 전달이 필요하다.
     * 해당 ExceptionHandler 는, 예외를 Ajax 에 body 로 전달해주는 기능을 한다.
     * **/

    @ExceptionHandler(AjaxResponseException.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
    }
}
