package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper){
        this.menuMapper = menuMapper;
    }

    public List<MenuDTO> findAllMenus() {
        return menuMapper.findAllMenus();
    }

    public List<CategoryDTO> findAllCategory() {
        return menuMapper.findAllCategory();
    }

    // Transactional : DML 작업을 하나씩 단위로 묶는 용도
    @Transactional
    public void registMenu(MenuDTO newMenu) {
        menuMapper.registNewMenu(newMenu);
    }

    public MenuDTO findOneMenu(int searchCode) {
        return menuMapper.findOneMenu(searchCode);
    }

    public List<MenuAndCategoryDTO> findAllMenuAndCategory() {
        return menuMapper.findAllMenuAndCategory();
    }

    public Map<String,Object> deleteCodeMenu(int deleteMenuCode) {

        Map<String,Object> deleteInfo = new HashMap<>();

        MenuDTO deletedMenu = menuMapper.findOneMenu(deleteMenuCode);
        int deleteStatus = menuMapper.deleteCodeMenu(deleteMenuCode);

        deleteInfo.put("deleteMenu",deletedMenu);
        deleteInfo.put("deleteStatus",deleteStatus);

        return deleteInfo;
    }

    public Map<String,Object> deleteCodeMenuList(List<Integer> checkCode) {
        Map<String,Object> deleteInfoList = new HashMap<>();

        List<MenuDTO> deletedMenuList = menuMapper.findFewMenu(checkCode);
        int deleteStatus = menuMapper.deleteCodeMenuList(checkCode);

        deleteInfoList.put("deletedMenuList",deletedMenuList);
        deleteInfoList.put("deleteStatus",deleteStatus);

        return deleteInfoList;
    }
}
