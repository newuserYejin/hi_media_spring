package com.ohgiraffers.thymeleaf;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lecture/*")
public class LectureController {

    @GetMapping("expression")
    public ModelAndView expression(ModelAndView mv){
        
        mv.addObject("member",new MemberDTO("조평훈",20,'남', "경기도 수원시"));

        mv.addObject("hello","hi~<h2>타임리프</h2>");

        mv.setViewName("lecture/expression");

        return mv;
    }

    @GetMapping("conditional")
    public ModelAndView conditional(ModelAndView mv){

        mv.addObject("num",1);
        mv.addObject("str","바나나");

        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("하츄핑",87,'여',"서울시 노진구"));
        memberList.add(new MemberDTO("시진핑",4,'여',"베이징 사천구"));
        memberList.add(new MemberDTO("핑핑",23,'남',"서울시 광진구"));
        memberList.add(new MemberDTO("퐁핑",6,'여',"서울시 피구"));
        memberList.add(new MemberDTO("하핑",4,'남',"서울시 핑구"));

        mv.addObject("memberList",memberList);

        mv.setViewName("lecture/conditional");

        return mv;
    }

    @GetMapping("etc")
    public ModelAndView etc(ModelAndView mv){
        SearchCriteria criteria = new SearchCriteria(1,10,3);

        mv.setViewName("lecture/etc");

        // 키를 작성하지 않으면 클래스 명이 키값이 된다. (아래에서는 SearchCriteria 가 키 값)
        mv.addObject(criteria);

        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("하츄핑",87,'여',"서울시 노진구"));
        memberList.add(new MemberDTO("시진핑",4,'여',"베이징 사천구"));
        memberList.add(new MemberDTO("핑핑",23,'남',"서울시 광진구"));
        memberList.add(new MemberDTO("퐁핑",6,'여',"서울시 피구"));
        memberList.add(new MemberDTO("하핑",4,'남',"서울시 핑구"));

        mv.addObject("memberList",memberList);

        Map<String,MemberDTO> memberMap = new HashMap<>();
        memberMap.put("1",new MemberDTO("하츄핑",87,'여',"서울시 노진구"));
        memberMap.put("2",new MemberDTO("시진핑",4,'여',"베이징 사천구"));
        memberMap.put("3",new MemberDTO("핑핑",23,'남',"서울시 광진구"));
        memberMap.put("4",new MemberDTO("퐁핑",6,'여',"서울시 피구"));
        memberMap.put("5",new MemberDTO("하핑",4,'남',"서울시 핑구"));

        mv.addObject("memberMap",memberMap);

        return mv;
    }

    @GetMapping("fragment")
    public ModelAndView fragment(ModelAndView mv){
        mv.addObject("test","value");
        mv.addObject("test2","value2");

        mv.setViewName("lecture/fragment");

        return mv;
    }

}
