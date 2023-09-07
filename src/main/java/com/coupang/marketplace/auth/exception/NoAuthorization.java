package com.coupang.marketplace.auth.exception;

public class NoAuthorization extends RuntimeException{

    private static final String message = "사용자 인증 정보가 없습니다.";

    public NoAuthorization(){
        super(message);
    }
}
