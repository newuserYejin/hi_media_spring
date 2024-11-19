package com.ohgiraffers.handlermethod;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("/request/*")
@SessionAttributes("id")
public class RequestController {

    // String 과  같은 반환타입을 지정하지 않는다면 요청 url 과 파일구조와 파일명이 같은
    // resource 하위의 해당 파일구조의 동일 명 html 을 응답페이지로 사용한다.
    @GetMapping("regist")
    public void regist(){}

    // WebRequest 는 Servlet 에서 HttpServletRequest 를 상속받은 Spring 특화다.
    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request){
        String menuName = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int category = Integer.parseInt(request.getParameter("category"));

        String message = menuName + "을(를) 신규 메뉴 목록 " + category + "번 카테고리에 " + price + "원으로 등록했습니다.";
        model.addAttribute("message",message);

        return "request/printResult";
    }

    @GetMapping("modify")
    public void modify(){}
    
    // name 속성이 일치하지 않으면  400-bad request Error 발생 -> required 속성의 기본 값이 true 이기 때문이다.
    // required 속성의 값을 false 로 바꾸면 error 발생 안되고 가능
    @PostMapping("modify")
    public String modifyMenu(Model model, @RequestParam("modifyName") String name, @RequestParam int modifyPrice,
                             @RequestParam(required = false) String test){
        String message = name + "의 가격을 " +modifyPrice + "로 수정!!" + "여기는 test 내용 : " + test ;
        // 없는 name에 대한 값을 요구했지만 error 없이 null 로 처리

        model.addAttribute("message",message);

        return "request/printResult";
    }

    @PostMapping("modifyAll")
    public String modifyAll(Model model, @RequestParam Map<String,String> parameters){
        String menuName = parameters.get("modifyName2");
        int menuPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = menuName + "의 가격을 " +menuPrice + "로 수정!!";
        model.addAttribute("message",message);

        return "request/printResult";
    }

    @GetMapping("search")
    public void search(){}

    // 한번에 받아올테이터가 많다면 형태 맞춰서 받아오기
    //()를 이용하여 이름 지정
    @PostMapping("search")
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu){

        System.out.println("menu = " + menu);
        
        return "request/searchResult";
    }

    // 아래의 경우에는 DTO의 앞에가 소문자인 menuDTO로 받아서 searchResult.html 에서 사용

//    @PostMapping("search")
//    public String searchMenu(@ModelAttribute MenuDTO menu){
//
//        System.out.println("menu = " + menu);
//
//        return "request/searchResult";
//    }

    @GetMapping("login")
    public void login(){}

    @PostMapping("login1")
    public String sessionTest(HttpSession session,
                              @RequestParam String id){
        session.setAttribute("id",id);

        return "request/loginResult";
    }
    
    @GetMapping("logout1")
    public String logout(HttpSession session){
        session.invalidate();   // 강제 만료
        return "request/loginResult";
    }

    // @SessionAttributes 를 이용한 session 에 값 담기
    // SessionAttributes 는 클래스 레벨에서 설정하며 key 값을 넣어주면 자동으로 session 에 해당 값을 저장해 준다.
    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id){

        model.addAttribute("id",id);

        return "request/loginResult";
    }

    @GetMapping("logout2")
    public String logout2(SessionStatus sessionStatus){

        sessionStatus.setComplete();

        return "request/loginResult";
    }

    @GetMapping("body")
    public void body(){}

    @PostMapping("body")
    public void bodyTest(@RequestBody String body) throws UnsupportedEncodingException {
        System.out.println("body = " + body);

        System.out.println(URLDecoder.decode(body,"UTF-8"));
    }
}
