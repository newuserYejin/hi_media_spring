package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HandleInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    @Autowired
    public HandleInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    // 전처리 메소드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandler 호출");

        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime",startTime);

        // true 값을 리턴하면 Interceptor 의 다음 계층으로 넘어가게 된다.
        // false 값을 리턴하면 Interceptor 의 다음 계층으로 넘어가지 않는다.
        return true;
    }

    // 후처리 메소드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandler 호출");

        long startTime = (long) request.getAttribute("startTime");

        long endTime = System.currentTimeMillis();

        modelAndView.addObject("interval", endTime - startTime);
    }

    // 가장 마지막 view 가 렌더링 된 이후 동작하는 메소드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterCompletion 호출");

        menuService.method();
    }

}
