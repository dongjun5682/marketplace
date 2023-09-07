package com.coupang.marketplace.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginCheck {


    /**
     * 로그인 체크 유저 로그인 타입
     * 회원(Member), 관리자(admin)
    */

    UserType type();

    public static enum UserType{
        MEMBER, ADMIN
    }
}
