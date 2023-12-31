package com.coupang.marketplace.auth;

import com.coupang.marketplace.auth.exception.NoAuthorization;
import com.coupang.marketplace.auth.exception.UnauthorizedException;
import com.coupang.marketplace.global.SessionKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if(isNeedToAuth((HandlerMethod) handler)){
                getUserBySession(request);
            }
            return true;
        }catch(Exception e){
            throw new UnauthorizedException(e);
        }

    }

    private boolean isNeedToAuth(HandlerMethod handlerMethod) {
        if(handlerMethod.getMethodAnnotation(AuthRequired.class) == null){
            return false;
        }
        return true;
    }

    private String getUserBySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return Optional.ofNullable(session.getAttribute(SessionKey.LOGIN_USER_ID))
                .map(v -> v.toString())
                .orElseThrow(NoAuthorization::new);
    }

}
