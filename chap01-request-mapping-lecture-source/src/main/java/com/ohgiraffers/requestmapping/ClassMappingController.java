package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

/*1. Class 레벨에 매핑하기*/
@RequestMapping("/order/*")
public class ClassMappingController {

    @GetMapping("/regist")
    public String registOrder(Model model){

        model.addAttribute("message","GET 방식의 주문등록 핸들러가 여기 있지!!!!");

        return "mappingResult";
    }
    
    // 2. 여러 URL 매핑하기
    @RequestMapping(value = {"modify","delete"},method = RequestMethod.POST)
    public String modifyAndDelete(Model model){
        model.addAttribute("message","POST 방식의 수정 삭제 동시 처리!");

        return "mappingResult";
    }

    // 3. path variable(url 경로를 타고 오는 값)
    @GetMapping("/detail/{orderNo}")
    public String orderDetail(Model model, @PathVariable int orderNo){

        model.addAttribute("message",orderNo + "번이 조회 되었습니다.");

        return "mappingResult";
    }


}
