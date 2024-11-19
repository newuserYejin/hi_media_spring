package com.ohgiraffers.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Interceptor 는 만든 후 WebMvcConfigurer 를 통해서 등록해야 한다.

    @Autowired
    private HandlerInterceptor handlerInterceptor;

    // 우리가 만든 Interceptor 를 Registry = 저장소에 등록하는 역할
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(handlerInterceptor)
                // 인터 셉트 동작 하는 url 설정
                .addPathPatterns("/*")      // 모든 url

                // static 파일을 불러오는 것도 하나의 요청으로
                // 매번 interceptor 가 호출되는게 비효율적이기 때문에 제외시킨다.

                .excludePathPatterns("/css/*")      // 제외 패턴 설정
                .excludePathPatterns("/asset/*")
                .excludePathPatterns("/js/*")
                .excludePathPatterns("/images/*");

    }
}
