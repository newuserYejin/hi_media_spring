package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

// Mybatis 의 전용 어노테이션으로 Repasitory 대신 사용
@Mapper
public interface MenuMapper {
    List<MenuDTO> findAllMenus();

    List<CategoryDTO> findAllCategory();

    void registNewMenu(MenuDTO newMenu);

    MenuDTO findOneMenu(int searchCode);

    List<MenuAndCategoryDTO> findAllMenuAndCategory();

    int deleteCodeMenu(int deleteMenuCode);

    List<MenuDTO> findFewMenu(List<Integer> checkCode);

    int deleteCodeMenuList(List<Integer> checkCode);
}
