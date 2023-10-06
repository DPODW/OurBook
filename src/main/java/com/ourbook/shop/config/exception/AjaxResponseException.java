package com.ourbook.shop.config.exception;

public class AjaxResponseException extends RuntimeException{

    public AjaxResponseException(String message) {
        super(message);
    }
}
