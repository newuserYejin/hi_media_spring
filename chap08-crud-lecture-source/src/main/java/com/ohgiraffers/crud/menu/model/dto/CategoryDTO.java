package com.ohgiraffers.crud.menu.model.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("category")
public class CategoryDTO {

    private int code;
    private String name;
    // 외래키 이기 때문에 Integer 로 받기
    private Integer refCategoryCode;
}
