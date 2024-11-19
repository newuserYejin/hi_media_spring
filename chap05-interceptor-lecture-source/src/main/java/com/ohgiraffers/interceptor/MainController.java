package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    // 파일명이 아닌 경로(URL)명과 일치는 HTML 파일이여야 void로 가능
    public String main(){
        return "main";
    }

}
