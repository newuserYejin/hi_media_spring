package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu/*")
public class MenuController {

    private final MenuService menuService;
    
    // 생성자를 통한 의존성 주입
    @Autowired
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }
    
    @GetMapping("list")
    public String findMenuList(Model model){
        
        List<MenuDTO> menuList =  menuService.findAllMenus();
        for (MenuDTO menu : menuList){
            System.out.println("menu = " + menu);
        }

        model.addAttribute("menuList",menuList);

        return "menu/list";
    }

    @GetMapping("regist")
    public void registPage(){}

//    @PostMapping("regist")

    // 호출이 js 이기 때문에 형태를 js에 맞춰서 return 해야한다. 따라서 produces 를 이용하여 형식을 맞춘다. json = js 객체 표기법
    @GetMapping(value = "category", produces = "application/json; charset=UTF-8;")
    // @ResponseBody 를 사용하면 view 리턴이 아닌 데이터를 리턴하게 된다.
    @ResponseBody
    public List<CategoryDTO> findCategoryList(){
        return menuService.findAllCategory();
    }

}
