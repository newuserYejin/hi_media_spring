package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
* 스프링 부트에는 servlet 이 내장되어 있다.
* 이제는 사용자의 요청을 받으면 @Controller 가 동작한다.
* */

@Controller
public class MethodMappingController {

    // 1. 메소드 방식 미지정 (servlet 에서는 service 와 비슷)
    @RequestMapping("/menu/regist")
    public String registerMenu(Model model){
        
        // model => 값을 담을 수 있는 공간. key & value 형식

        model.addAttribute("message","메뉴 등록용 핸들러 메소드 동작 확인....");
        
        // 사용자에게 응답 돌려주기

        /*
        * 반환 타입을 String 으로 해서 전달하면 resource/templates 를 탐색하여
        * String 과 같은 이름의 파일을 찾게 된다.
        * */
        
        return "mappingResult";
    }

    // 메소드 지정 방식
    @RequestMapping(value = "/menu/modify", method = RequestMethod.POST)
    public String modifyMenu(Model model){
        model.addAttribute("message","GET 방식만 허용하는 방법 호이짜짜짜짜짜짜짜짜짜짜짜짜짜");

        return "mappingResult";
    }

    // @RequestMapping 방식만울 쓰면 항상 method 방식을 작성해야한다.
    // POST -> @PostMapping
    // GET -> @GETMapping
    // PUT -> @PutMapping
    // DELETE -> @DeleteMapping

    @GetMapping("/menu/delete")
    public String getDeleteMenu(Model model){

        model.addAttribute("message", "GET 방식의 메뉴 삭제용 핸들러임!!!!");

        return "mappingResult";
    }

    @PostMapping("/menu/delete")
    public String postDeleteMenu(Model model){

        model.addAttribute("message", "요건 POST 방식의 메뉴 삭제용 핸들러임!!!!");

        return "mappingResult";
    }

}
