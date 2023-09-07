package com.coupang.marketplace.aop;

import com.coupang.marketplace.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/*@Aspect
@Component*/
@Slf4j
public class AuthCheckAop {

    private final LoginService loginService;

    public AuthCheckAop(LoginService loginService) {
        this.loginService = loginService;
    }

    @Before("@annotation(com.coupang.marketplace.aop.MemberLoginCheck)")
    public void memberLoginCheck(JoinPoint joinPoint){
        log.info("AOP - Member Login Check Started");

        HttpSession session = getSession();
        long loginUserId = loginService.getLoginUserId();

        if (loginUserId == 0) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED,"SESSION_LOGIN_FAIL") {};
        }
    }

    @Before("@annotation(com.coupang.marketplace.aop.LoginCheck) && @@annotation(loginCheck)")
    public void loginCheck(JoinPoint joinPoint, LoginCheck loginCheck){
        log.info("AOP - Login Check Started");
        HttpSession session = getSession();

        if (LoginCheck.UserType.MEMBER.equals(loginCheck.type())) {
            memberLoginCheck(joinPoint);
        }
    }

    private HttpSession getSession(){
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
    }

}
