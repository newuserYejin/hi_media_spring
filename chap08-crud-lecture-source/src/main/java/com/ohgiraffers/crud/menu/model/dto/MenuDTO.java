package com.ohgiraffers.crud.menu.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenuDTO {

    // DTO 는 DataBAse 컬럼과 일치하게 작성하거나 request 하는 데이터를 작성한다.

    private int code;
    private String name;
    private int price;
    private int categoryCode;
    private String orderableStastus;

}
