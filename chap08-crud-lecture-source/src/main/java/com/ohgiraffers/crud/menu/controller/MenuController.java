package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.exception.MenuNotFoundException;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/menu/*")
public class MenuController {
    
    // 로깅 추가
    // Logging : 어플리케이션이 실행 중 발생하는 이벤트(정보, 경오, 오류) 등을 기록하는 과정

    private static final Logger logger = LogManager.getLogger(MenuController.class);

    private final MenuService menuService;

    // bean 으로 등록한 message 사용
    private final MessageSource messageSource;
    
    // 생성자를 통한 의존성 주입
    @Autowired
    public MenuController(MenuService menuService, MessageSource messageSource){
        this.menuService = menuService;
        this.messageSource = messageSource;
    }

    @GetMapping("selectOne")
    public void findOneMenu(Model model, @RequestParam int searchCode) throws MenuNotFoundException {
        MenuDTO searchMenu = menuService.findOneMenu(searchCode);

        if (searchMenu == null) {
            throw new MenuNotFoundException("해당 번호의 메뉴를 찾을 수 없습니다.");
        }

        model.addAttribute("searchMenu",searchMenu);
    }
    
    @GetMapping("list")
    public String findMenuList(Model model){
        
        List<MenuDTO> menuList =  menuService.findAllMenus();

        model.addAttribute("menuList",menuList);

        return "menu/list";
    }

    @GetMapping("regist")
    public void registPage(){}

    // @ModelAttribute : form 태그에서 넘어오는 값을 클래스 자료형에 담기 위한 어노테이션
    // RedirectAttributes : 리다이랙트 시 저장할 값이 있으면 사용하는 객체
    // Locale : 접속 위치 정보
    @PostMapping("regist")
    public String registMenu(@ModelAttribute MenuDTO newMenu, RedirectAttributes rttr, Locale locale){

        // TRACE : 상세한 디버깅 정보
        // DEBUG :  개발 중 디버깅용 정보
        // INFO : 일반적인 실행 정보
        // WARN : 잠재적인 문제 경고
        // ERROR : 실행 중에 발생한 오류
        
        logger.info("Locale:{}",locale);
        logger.info("newMenu:{}",newMenu);

        menuService.registMenu(newMenu);
        rttr.addFlashAttribute("successMessage",
                            messageSource.getMessage("regist",new Object[]{newMenu.getName(), newMenu.getPrice()},locale));

        return "redirect:/menu/list";
    }

    // 호출이 js 이기 때문에 형태를 js에 맞춰서 return 해야한다. 따라서 produces 를 이용하여 형식을 맞춘다. json = js 객체 표기법
    // @ResponseBody 를 사용하면 view 리턴이 아닌 데이터를 리턴하게 된다.
    @GetMapping(value = "category", produces = "application/json; charset=UTF-8;")
    @ResponseBody
    public List<CategoryDTO> findCategoryList(){
        return menuService.findAllCategory();
    }

    @GetMapping("detail")
    public ModelAndView showMenuDetail(ModelAndView mv, @RequestParam int menuCode){

        MenuDTO detailMenu = menuService.findOneMenu(menuCode);

        mv.setViewName("menu/selectOne");
        mv.addObject("searchMenu",detailMenu);

        return mv;
    }

    @PostMapping("test")
    public void testInput(Model model,@RequestParam("testText") String testText,@RequestParam String testTextt,@RequestParam int testNum ){
        System.out.println("testText = " + testText);
        System.out.println("testTextt = " + testTextt);
        System.out.println("testNum = " + testNum);
    }

    @GetMapping("join/list")
    public String menuAndCategoryList(Model model){
        List<MenuAndCategoryDTO> joinList = menuService.findAllMenuAndCategory();

        model.addAttribute("joinList",joinList);

        return "menu/join";
    }

    // delete 누르면 코드 입력 화면으로 이동 -> 이후 값을 전달 받아 삭제. menu/list 로 redirect & 삭제 메뉴 정보 알려주기
    @GetMapping("delete")
    public void deleteMenu(){}

    @PostMapping("delete")
    public ModelAndView deleteCodeMenu(ModelAndView mv,@RequestParam int deleteMenuCode, RedirectAttributes rttr, Locale locale){

        Map<String, Object> deletedInfo = menuService.deleteCodeMenu(deleteMenuCode);

        MenuDTO deletedMenu = (MenuDTO) deletedInfo.get("deleteMenu");
        int deleteStatus = (int)deletedInfo.get("deleteStatus");

        System.out.println("deletedInfo = " + deletedInfo);
        System.out.println("deletedMenu = " + deletedMenu);
        System.out.println("deleteStatus = " + deleteStatus);

        if (deleteStatus > 0){
            rttr.addFlashAttribute("deleteMessage",
                    messageSource.getMessage("delete",new Object[]{deletedMenu.getCode(), deletedMenu.getName(),"삭제되었"},locale));
        } else {
            rttr.addFlashAttribute("deleteMessage",
                    messageSource.getMessage("delete",new Object[]{deletedMenu.getCode(), deletedMenu.getName(),"삭제에 실패했."},locale));
        }

        mv.setViewName("redirect:/menu/list");

        return mv;
    }

    @PostMapping("deleteMenus")
    public ModelAndView deleteMenus(ModelAndView mv, @RequestParam List checkCode, RedirectAttributes rttr, Locale locale){
        System.out.println("checkCode = " + checkCode);
        Map<String, Object> deletedInfo = menuService.deleteCodeMenuList(checkCode);

        List<MenuDTO> deletedMenuList = (List<MenuDTO>) deletedInfo.get("deletedMenuList");
        int deleteStatus = (int)deletedInfo.get("deleteStatus");

        System.out.println("deletedInfo = " + deletedInfo);
        System.out.println("deletedMenu = " + deletedMenuList);
        System.out.println("deleteStatus = " + deleteStatus);

        String sendMessage = "";

        if (deleteStatus > 1){

            for (MenuDTO deleteMenu : deletedMenuList){
                sendMessage += messageSource.getMessage("delete",new Object[]{deleteMenu.getCode(),deleteMenu.getName(),"삭제되었"},locale);
            }

            System.out.println("sendMessage = " + sendMessage);

            rttr.addFlashAttribute("deleteMessage",sendMessage);
        } else {
            rttr.addFlashAttribute("deleteMessage",
                    messageSource.getMessage("delete",new Object[]{deletedMenuList.get(0).getCode(), deletedMenuList.get(0).getName(),"삭제되었"},locale));
        }

        mv.setViewName("redirect:/menu/list");

        return mv;
    }

    @GetMapping(value = "liveSearch", produces = "application/json; charset=UTF-8;")
    @ResponseBody
    public MenuDTO LiveSearch(@RequestParam("searchCode") int searchCode) throws MenuNotFoundException {

        MenuDTO searchMenu = menuService.findOneMenu(searchCode);

        if (searchMenu == null) {
            throw new MenuNotFoundException("해당 번호의 메뉴를 찾을 수 없습니다.");
        }

        return searchMenu;
    }

}
