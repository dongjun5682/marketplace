package com.coupang.marketplace.config;

import com.coupang.marketplace.auth.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
        /*
        Annotation을 사용하지 않고 URL 패턴으로만 할때 적용
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/user/**") // 인터셉터가 실행될 URL 패턴
                .excludePathPatterns("/product/**"); // 인터셉터가 실행되지 않을 URL 패턴*/
    }
}
